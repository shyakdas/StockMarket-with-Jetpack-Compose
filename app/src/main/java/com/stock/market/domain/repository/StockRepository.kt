package com.stock.market.domain.repository

import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.CompanyListing
import com.stock.market.domain.model.IntradayInfo
import com.stock.market.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * This interface defines the contract for a StockRepository, which is responsible for providing
 * methods to access and manage stock-related data.
 */
interface StockRepository {

    /**
     * Retrieves a list of company listings either from the remote data source (API) or
     * the local database cache, based on the provided parameters.
     *
     * @param fetchFromRemote A boolean indicating whether to fetch data from the remote API.
     * @param query The search query to filter company listings (optional).
     *
     * @return A Flow of Resource objects representing the status and data of the operation.
     */
    suspend fun getCompanyListing(fetchFromRemote: Boolean, query: String): Flow<Resource<List<CompanyListing>>>

    /**
     * Retrieves intraday stock information for a given stock symbol from a remote data source.
     *
     * @param symbol The stock symbol for which to fetch intraday information.
     *
     * @return A Resource object representing the status and data of the operation.
     */
    suspend fun getInrtadayInfo(symbol :String) : Resource<List<IntradayInfo>>

    /**
     * Retrieves detailed information about a company for a given stock symbol from a remote data source.
     *
     * @param symbol The stock symbol for which to fetch company information.
     *
     * @return A Resource object representing the status and data of the operation.
     */
    suspend fun getCompanyInfo(symbol: String) : Resource<CompanyInfo>
}