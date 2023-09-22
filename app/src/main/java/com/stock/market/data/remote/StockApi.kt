package com.stock.market.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(@Query("apiKey") apiKey : String = API_KEY) : ResponseBody

    companion object {
        const val API_KEY = "JWPB7P55IV3D26M3"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}