package com.stock.market.di

import com.stock.market.data.csv.CSVParser
import com.stock.market.data.csv.CompanyListingsParser
import com.stock.market.data.csv.IntradayInfoParser
import com.stock.market.data.repository.StockRepositoryImpl
import com.stock.market.domain.model.CompanyListing
import com.stock.market.domain.model.IntradayInfo
import com.stock.market.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(companyListingParser: CompanyListingsParser): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepositoryImpl: StockRepositoryImpl) : StockRepository

    @Binds
    @Singleton
    abstract fun bindIntradayParser(intradayInfoParser: IntradayInfoParser): CSVParser<IntradayInfo>
}