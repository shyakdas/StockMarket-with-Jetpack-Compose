package com.stock.market.domain.model

import java.time.LocalDateTime

/**
 * A data class representing intraday stock information, including the date and closing price.
 *
 * @property date The date and time at which the intraday information was recorded (LocalDateTime).
 * @property close The closing price of the stock at the recorded date and time.
 */
data class IntradayInfo(val date : LocalDateTime, val close : Double)