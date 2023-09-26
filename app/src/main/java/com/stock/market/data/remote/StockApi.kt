package com.stock.market.data.remote

import com.stock.market.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface representing the Stock API.
 *
 * This interface defines the contract for interacting with a Stock API, although it currently
 * doesn't contain any specific methods or functionality. It serves as a placeholder for
 * potential future API-related methods and can be extended with methods for retrieving,
 * updating, or manipulating stock-related data.
 */
interface StockApi {

    /**
     * A Retrofit API endpoint for retrieving stock listings' status.
     *
     * This Retrofit interface defines a method for making a network request to the specified
     * endpoint to fetch stock listings' status. It uses the HTTP GET method and allows specifying
     * an API key as a query parameter, with a default value of the application's API key constant.
     *
     * @param apiKey The API key used for authentication (defaulting to the application's API key).
     * @return A ResponseBody object representing the response from the API call.
     */
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(@Query("apikey") apiKey: String = API_KEY): ResponseBody

    /**
     * A Retrofit API endpoint for retrieving intraday time series data.
     *
     * This Retrofit interface defines a method for making a network request to the specified
     * endpoint to fetch intraday time series data for a specific stock symbol. It uses the HTTP
     * GET method and allows specifying the stock symbol and an API key as query parameters.
     * The interval and data type are set as constants for this specific endpoint.
     *
     * @param symbol The symbol of the stock for which to retrieve intraday data.
     * @param apiKey The API key used for authentication (defaulting to the application's API key).
     * @return A ResponseBody object representing the response from the API call.
     */
    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(@Query("symbol") symbol: String, @Query("apikey") apiKey: String = API_KEY): ResponseBody

    /**
     * A Retrofit API endpoint for retrieving company overview information.
     *
     * This Retrofit interface defines a method for making a network request to the specified
     * endpoint to fetch company overview information for a specific stock symbol. It uses the HTTP
     * GET method and allows specifying the stock symbol and an API key as query parameters.
     * The data type expected in the response is a CompanyInfoDto.
     *
     * @param symbol The symbol of the stock for which to retrieve company overview information.
     * @param apiKey The API key used for authentication (defaulting to the application's API key).
     * @return A CompanyInfoDto object representing the response from the API call.
     */
    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(@Query("symbol") symbol : String, @Query("apikey") apiKey: String = API_KEY) : CompanyInfoDto

    /**
     * Constants for the StockApi configuration.
     *
     * This companion object defines constants related to the StockApi configuration, including
     * the API key and base URL for making API requests to retrieve stock data from the Alpha Vantage
     * service.
     *
     * @property API_KEY The API key used for authentication with the Alpha Vantage API.
     * @property BASE_URL The base URL for Alpha Vantage API endpoints.
     */
    companion object {
        const val API_KEY = "JWPB7P55IV3D26M3"
        const val BASE_URL = "https://alphavantage.co/"
    }
}