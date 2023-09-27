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

/**
 * This abstract class defines the RepositoryModule, which serves as a Dagger Hilt module for
 * providing repository-related dependencies. It is annotated with @Module and installed in
 * the SingletonComponent to ensure the singleton scope for its provided dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * This abstract function binds an implementation of the CSVParser interface for CompanyListing
     * to the provided CompanyListingsParser instance. It ensures that a singleton scope is applied
     * to the binding.
     *
     * @param companyListingParser The implementation of CSVParser for CompanyListing.
     *
     * @return A bound instance of CSVParser for CompanyListing.
     */
    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(companyListingParser: CompanyListingsParser): CSVParser<CompanyListing>

    /**
     * This abstract function binds the `StockRepositoryImpl` implementation to the `StockRepository`
     * interface. It ensures that a singleton scope is applied to the binding.
     *
     * @param stockRepositoryImpl The implementation of `StockRepository`.
     *
     * @return A bound instance of `StockRepository`.
     */
    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepositoryImpl: StockRepositoryImpl) : StockRepository

    /**
     * This abstract function binds an implementation of the CSVParser interface for IntradayInfo
     * to the provided IntradayInfoParser instance. It ensures that a singleton scope is applied
     * to the binding.
     *
     * @param intradayInfoParser The implementation of CSVParser for IntradayInfo.
     *
     * @return A bound instance of CSVParser for IntradayInfo.
     */
    @Binds
    @Singleton
    abstract fun bindIntradayParser(intradayInfoParser: IntradayInfoParser): CSVParser<IntradayInfo>
}