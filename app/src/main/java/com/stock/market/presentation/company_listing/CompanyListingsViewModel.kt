package com.stock.market.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stock.market.domain.repository.StockRepository
import com.stock.market.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A ViewModel class annotated with @HiltViewModel representing the CompanyListingsViewModel.
 *
 * @param repository A StockRepository for accessing company listings data.
 */
@HiltViewModel
class CompanyListingsViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {

    /**
     * A mutable state variable used to represent the state of CompanyListing information in the UI.
     */
    var state by mutableStateOf(CompanyListingState())

    /**
     * A private nullable Job variable used for managing search operations.
     */
    private var searchJob: Job? = null

    /**
     * An initialization block that triggers the `getCompanyListings` function.
     */
    init {
        getCompanyListings()
    }

    /**
     * A function for handling various CompanyListing events and triggering corresponding actions.
     *
     * @param event The CompanyListingEvent to handle.
     */
    fun onEvent(event: CompanyListingEvent) {
        when (event) {
            is CompanyListingEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }

            is CompanyListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    /**
     * A private function for fetching company listings based on the provided query and fetchFromRemote flag.
     *
     * @param query The search query to filter company listings.
     * @param fetchFromRemote A boolean indicating whether to fetch data from the remote API.
     */
    private fun getCompanyListings(
        query: String = state.searchQuery,
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getCompanyListing(fetchFromRemote, query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            state = state.copy(
                                companies = listings
                            )
                        }
                    }

                    is Resource.Error -> Unit

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}