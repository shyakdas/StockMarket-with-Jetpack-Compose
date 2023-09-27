package com.stock.market.domain.model

/**
 * A data class representing a listing of a company, including its name, symbol, and exchange information.
 *
 * @property name The name of the company.
 * @property symbol The stock symbol of the company.
 * @property exchange The exchange where the company is listed.
 */
data class CompanyListing(
    val name: String,
    val symbol: String,
    val exchange: String
)
