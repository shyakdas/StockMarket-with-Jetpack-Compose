package com.stock.market.mapper

import com.stock.market.data.local.CompanyListingEntity
import com.stock.market.data.mapper.toCompanyInfo
import com.stock.market.data.mapper.toCompanyListing
import com.stock.market.data.mapper.toCompanyListingEntity
import com.stock.market.data.remote.dto.CompanyInfoDto
import com.stock.market.domain.model.CompanyListing
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

class CompanyMapperTest {

    @Test
    fun testToCompanyListing() {
        // Create a mock for CompanyListingEntity
        val mockEntity = mock(CompanyListingEntity::class.java)

        // Define the values you want to return when properties are accessed
        `when`(mockEntity.name).thenReturn("Company Name")
        `when`(mockEntity.symbol).thenReturn("SYM")
        `when`(mockEntity.exchange).thenReturn("NYSE")

        // Call the toCompanyListing() function
        val companyListing = mockEntity.toCompanyListing()

        // Verify that CompanyListing is created correctly
        assertEquals("Company Name", companyListing.name)
        assertEquals("SYM", companyListing.symbol)
        assertEquals("NYSE", companyListing.exchange)

        // Verify that the properties of mockEntity were accessed
        verify(mockEntity).name
        verify(mockEntity).symbol
        verify(mockEntity).exchange

        // Optionally, you can verify that there were no other interactions with the mock
        verifyNoMoreInteractions(mockEntity)
    }

    @Test
    fun testToCompanyListingEntity() {
        // Create a mock for CompanyListing
        val mockListing = mock(CompanyListing::class.java)

        // Define the values you want to return when properties are accessed
        `when`(mockListing.name).thenReturn("Company Name")
        `when`(mockListing.symbol).thenReturn("SYM")
        `when`(mockListing.exchange).thenReturn("NYSE")

        // Call the toCompanyListingEntity() function
        val companyListingEntity = mockListing.toCompanyListingEntity()

        // Verify that CompanyListingEntity is created correctly
        assertEquals("Company Name", companyListingEntity.name)
        assertEquals("SYM", companyListingEntity.symbol)
        assertEquals("NYSE", companyListingEntity.exchange)

        // Verify that the properties of mockListing were accessed
        verify(mockListing).name
        verify(mockListing).symbol
        verify(mockListing).exchange

        // Optionally, you can verify that there were no other interactions with the mock
        verifyNoMoreInteractions(mockListing)
    }

    @Test
    fun testToCompanyInfo() {
        // Create a mock for CompanyInfoDto
        val mockDto = mock(CompanyInfoDto::class.java)

        // Define the values you want to return when properties are accessed
        `when`(mockDto.symbol).thenReturn("SYM")
        `when`(mockDto.description).thenReturn("Company Description")
        `when`(mockDto.name).thenReturn("Company Name")
        `when`(mockDto.country).thenReturn("United States")
        `when`(mockDto.industry).thenReturn("Technology")

        // Call the toCompanyInfo() function
        val companyInfo = mockDto.toCompanyInfo()

        // Verify that CompanyInfo is created correctly
        assertEquals("SYM", companyInfo.symbol)
        assertEquals("Company Description", companyInfo.description)
        assertEquals("Company Name", companyInfo.name)
        assertEquals("United States", companyInfo.country)
        assertEquals("Technology", companyInfo.industry)

        // Verify that the properties of mockDto were accessed
        verify(mockDto).symbol
        verify(mockDto).description
        verify(mockDto).name
        verify(mockDto).country
        verify(mockDto).industry

        // Optionally, you can verify that there were no other interactions with the mock
        verifyNoMoreInteractions(mockDto)
    }
}