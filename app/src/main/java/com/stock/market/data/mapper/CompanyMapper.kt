package com.stock.market.data.mapper

import com.stock.market.data.local.CompanyListingEntity
import com.stock.market.data.remote.dto.CompanyInfoDto
import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.CompanyListing

/**
 * Converts a CompanyListingEntity object to a CompanyListing object.
 *
 * This function takes a CompanyListingEntity and creates a corresponding CompanyListing
 * object with the provided name, symbol, and exchange information. It is useful for mapping
 * data between different layers of an application, such as from a database entity to a
 * domain model.
 *
 * @return A new CompanyListing object with the same name, symbol, and exchange values
 * as the input CompanyListingEntity.
 */
fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name, symbol = symbol, exchange = exchange
    )
}

/**
 * Converts a CompanyListing object to a CompanyListingEntity object.
 *
 * This function takes a CompanyListing and creates a corresponding CompanyListingEntity
 * object with the provided name, symbol, and exchange information. It is useful for mapping
 * data between different layers of an application, such as from a domain model to a
 * database entity.
 *
 * @return A new CompanyListingEntity object with the same name, symbol, and exchange values
 * as the input CompanyListing.
 */
fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name, symbol = symbol, exchange = exchange
    )
}

/**
 * Converts a CompanyInfoDto object to a CompanyInfo object.
 *
 * This function takes a CompanyInfoDto and creates a corresponding CompanyInfo
 * object with the provided symbol, description, name, country, and industry information.
 * It is useful for mapping data received from an external source, such as an API response,
 * to a domain model within the application.
 *
 * @return A new CompanyInfo object with the same symbol, description, name, country, and
 * industry values as the input CompanyInfoDto. If any of these values are null in the
 * input, they are replaced with empty strings in the output.
 */
fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}