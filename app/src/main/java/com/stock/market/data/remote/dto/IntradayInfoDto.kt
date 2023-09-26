package com.stock.market.data.remote.dto

/**
 * Data class representing intraday trading information received from an external source as a DTO.
 *
 * This data class holds information such as timestamp and closing price, typically obtained
 * from an external data source like a financial data API response. It is used for mapping this
 * external data to a structured format within the application.
 *
 * @param timestamp The timestamp indicating the time of the intraday data point.
 * @param close The closing price of the asset or security for the given timestamp.
 */
data class IntradayInfoDto(
    val timestamp: String,
    val close: Double
)