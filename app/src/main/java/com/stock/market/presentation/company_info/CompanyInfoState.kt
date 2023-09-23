package com.stock.market.presentation.company_info

import com.stock.market.domain.model.CompanyInfo
import com.stock.market.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)