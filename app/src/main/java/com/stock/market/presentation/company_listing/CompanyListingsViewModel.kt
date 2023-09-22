package com.stock.market.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.stock.market.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {

    val state by mutableStateOf(CompanyListingState())

    fun onEvent(event: CompanyListingEvent) {
        when (event) {
            is CompanyListingEvent.Refresh -> {

            }

            is CompanyListingEvent.OnSearchQueryChange -> {

            }
        }
    }


}