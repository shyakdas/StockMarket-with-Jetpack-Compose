package com.stock.market.data.remote.dto

import com.squareup.moshi.Json

/**
 * Data class representing company information received from an external source as a DTO.
 *
 * This data class holds information such as symbol, description, name, country, and industry
 * that is typically obtained from an external data source, like an API response. It is used
 * for mapping this external data to a structured format within the application.
 *
 * @param symbol The symbol or ticker of the company.
 * @param description A brief description of the company.
 * @param name The name of the company.
 * @param country The country where the company is located.
 * @param industry The industry to which the company belongs.
 */
data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?
)