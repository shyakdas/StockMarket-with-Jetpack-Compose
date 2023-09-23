package com.stock.market.domain.repository

import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.CompanyListing
import com.stock.market.domain.model.IntradayInfo
import com.stock.market.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getInrtadayInfo(symbol :String) : Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(symbol: String) : Resource<CompanyInfo>
}