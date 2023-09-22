package com.stock.market.data.repository

import com.opencsv.CSVReader
import com.stock.market.data.local.StockDatabase
import com.stock.market.data.mapper.toCompanyListing
import com.stock.market.data.remote.StockApi
import com.stock.market.domain.model.CompanyListing
import com.stock.market.domain.repository.StockRepository
import com.stock.market.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(val api: StockApi, val db: StockDatabase) :
    StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListing.map {
                it.toCompanyListing()
            }))
            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            val remoteListing = try {
                val response = api.getListing()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}