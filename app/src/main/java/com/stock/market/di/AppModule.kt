package com.stock.market.di

import android.app.Application
import androidx.room.Room
import com.stock.market.data.local.StockDatabase
import com.stock.market.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * This Kotlin object defines the AppModule, which is responsible for providing application-level
 * dependencies using Hilt's dependency injection. It is annotated with @Module and installed in
 * the SingletonComponent to ensure the singleton scope for its provided dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the StockApi interface, which is responsible for making
     * network requests related to stock data.
     *
     * @return A singleton instance of StockApi.
     */
    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder().baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build().create()
    }

    /**
     * Provides a singleton instance of the StockDatabase, which is responsible for local storage
     * and retrieval of stock data. The database is built using Room and is given the name "stockdb.db".
     *
     * @param app The Application context required for database creation.
     *
     * @return A singleton instance of StockDatabase.
     */
    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(app, StockDatabase::class.java, "stockdb.db").build()
    }
}