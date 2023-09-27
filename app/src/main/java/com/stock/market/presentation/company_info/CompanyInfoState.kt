package com.stock.market.presentation.company_info

import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.IntradayInfo

/**
 * A data class representing the state of CompanyInfo information in the UI.
 *
 * @property stockInfos A list of IntradayInfo representing stock information.
 * @property companyInfo A CompanyInfo object representing detailed information about a company.
 * @property isLoading A boolean indicating whether data is currently being loaded.
 * @property error A string containing an error message, if any.
 */
data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)