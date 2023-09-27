package com.stock.market.data.repository

import com.stock.market.data.csv.CSVParser
import com.stock.market.data.local.StockDatabase
import com.stock.market.data.mapper.toCompanyInfo
import com.stock.market.data.mapper.toCompanyListing
import com.stock.market.data.mapper.toCompanyListingEntity
import com.stock.market.data.remote.StockApi
import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.CompanyListing
import com.stock.market.domain.model.IntradayInfo
import com.stock.market.domain.repository.StockRepository
import com.stock.market.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class represents the implementation of the StockRepository interface, providing
 * methods for accessing and managing stock-related data. It is annotated with @Singleton
 * to ensure a single instance of this class is used throughout the application.
 *
 * @param api The StockApi instance for making network requests to fetch stock data.
 * @param db The StockDatabase instance for local storage and retrieval of stock data.
 * @param companyListingParser The CSVParser for parsing company listing data.
 * @param intradayInfoParser The CSVParser for parsing intraday stock information.
 */
@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
) : StockRepository {

    /**
     * This private property represents the Data Access Object (DAO) used for database operations
     * within the StockRepositoryImpl class. It is initialized with the DAO instance obtained from
     * the StockDatabase.
     */
    private val dao = db.dao

    /**
     * Retrieves a list of company listings either from the remote data source (API) or
     * the local database cache, based on the provided parameters.
     *
     * @param fetchFromRemote A boolean indicating whether to fetch data from the remote API.
     * @param query The search query to filter company listings (optional).
     *
     * @return A Flow of Resource objects representing the status and data of the operation.
     */
    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            // Retrieve company listings from the local database
            val localListings = dao.searchCompanyListing(query)
            // Emit a success resource with the locally retrieved data
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))
            // Check if the local database is empty and the query is blank
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            // Check if we should just load data from the cache (local database)
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            // Attempt to fetch data from the remote API
            val remoteListings = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                // Clear and insert the fetched data into the local database
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listings.map { it.toCompanyListingEntity() }
                )
                // Emit a success resource with the newly updated data from the database
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    /**
     * Retrieves intraday stock information for a given symbol from the remote data source (API).
     *
     * @param symbol The stock symbol for which to fetch intraday information.
     *
     * @return A Resource object representing the status and data of the operation.
     */
    override suspend fun getInrtadayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            // Fetch intraday information from the remote API
            val response = api.getIntradayInfo(symbol)
            val result = intradayInfoParser.parse(response.byteStream())
            // Return a success resource with the fetched data
            Resource.Success(data = result)
        } catch (e: IOException) {
            e.printStackTrace()
            // Return an error resource with an error message
            Resource.Error(message = "Couldn't load intraday info")
        } catch (e: HttpException) {
            e.printStackTrace()
            // Return an error resource with an error message
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    /**
     * Retrieves detailed information about a company for a given stock symbol from the remote data source (API).
     *
     * @param symbol The stock symbol for which to fetch company information.
     *
     * @return A Resource object representing the status and data of the operation.
     */
    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            // Fetch company information from the remote API
            val result = api.getCompanyInfo(symbol)
            // Return a success resource with the fetched data, transformed to CompanyInfo
            Resource.Success(result.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            // Return an error resource with an error message
            Resource.Error(message = "Couldn't load intraday info")
        } catch (e: HttpException) {
            e.printStackTrace()
            // Return an error resource with an error message
            Resource.Error(message = "Couldn't load intraday info")
        }
    }
}