package com.stock.market.data.remote

import com.stock.market.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(@Query("apikey") apiKey: String = API_KEY): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datetype=csv")
    suspend fun getIntradayInfo(@Query("symbol") symbol : String, @Query("apikey") apiKey: String = API_KEY) : ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(@Query("symbol") symbol : String, @Query("apikey") apiKey: String = API_KEY) : CompanyInfoDto

    companion object {
        const val API_KEY = "JWPB7P55IV3D26M3"
        const val BASE_URL = "https://alphavantage.co/"
    }
}