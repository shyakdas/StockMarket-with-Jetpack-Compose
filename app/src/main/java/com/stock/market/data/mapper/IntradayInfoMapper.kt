package com.stock.market.data.mapper

import com.stock.market.data.remote.dto.IntradayInfoDto
import com.stock.market.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Converts an IntradayInfoDto object to an IntradayInfo object.
 *
 * This function takes an IntradayInfoDto and creates a corresponding IntradayInfo
 * object with the provided timestamp and close price information. It is useful for
 * parsing and mapping data from an external source, such as a financial data API response,
 * to a domain model object.
 *
 * @return A new IntradayInfo object with the date parsed from the timestamp string
 * according to the "yyyy-MM-dd HH:mm:ss" pattern and the provided close price.
 */
fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(date = localDateTime, close)
}