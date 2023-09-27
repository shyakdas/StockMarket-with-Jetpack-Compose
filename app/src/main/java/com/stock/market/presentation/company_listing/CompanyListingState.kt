package com.stock.market.presentation.company_listing

import com.stock.market.domain.model.CompanyListing

/**
 * A data class representing the state of CompanyListing information in the UI.
 *
 * @property companies A list of CompanyListing representing company listings.
 * @property isLoading A boolean indicating whether data is currently being loaded.
 * @property isRefreshing A boolean indicating whether a refresh operation is in progress.
 * @property searchQuery The current search query for filtering company listings.
 */
data class CompanyListingState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
