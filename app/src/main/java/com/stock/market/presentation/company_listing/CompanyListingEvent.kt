package com.stock.market.presentation.company_listing

/**
 * A sealed class representing events related to company listings.
 */
sealed class CompanyListingEvent {

    /**
     * An object that represents a specific event of refreshing company listings.
     */
    object Refresh : CompanyListingEvent()

    /**
     * A data class representing an event of a search query change for company listings.
     *
     * @property query The new search query.
     */
    data class OnSearchQueryChange(val query: String) : CompanyListingEvent()
}