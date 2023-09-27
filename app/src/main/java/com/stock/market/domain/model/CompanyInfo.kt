package com.stock.market.domain.model

/**
 * A data class representing detailed information about a company.
 *
 * @property symbol The stock symbol of the company.
 * @property description A brief description of the company.
 * @property name The name of the company.
 * @property country The country where the company is headquartered.
 * @property industry The industry to which the company belongs.
 */
data class CompanyInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String
)