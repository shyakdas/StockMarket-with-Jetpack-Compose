package com.stock.market.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This data class represents a CompanyListingEntity, which is typically used as an
 * entity in a database or for data storage. It includes fields for the name, symbol,
 * and exchange of a company listing, along with an optional primary key field.
 *
 * @param name The name of the company.
 * @param symbol The symbol or abbreviation associated with the company.
 * @param exchange The stock exchange where the company is listed.
 * @param id An optional primary key for database storage, defaulting to null.
 */
@Entity
data class CompanyListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)