package com.stock.market.local

import com.stock.market.data.local.CompanyListingEntity
import com.stock.market.data.local.StockDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CompanyListingEntityTest {

    private lateinit var stockDao: StockDao

    @Before
    fun setUp() {
        stockDao = mock(StockDao::class.java)
    }

    @Test
    fun testInsertCompanyListing() = runBlocking {
        // Create a CompanyListingEntity instance
        val companyListingEntity = CompanyListingEntity(
            name = "Company Name",
            symbol = "SYM",
            exchange = "NYSE"
        )

        // Call the function that inserts the entity into the database
        stockDao.insertCompanyListing(listOf(companyListingEntity))

        // Verify that the insertCompanyListing function was called with the expected entity
        verify(stockDao).insertCompanyListing(listOf(companyListingEntity))
    }

    @Test
    fun testSearchCompanyListing() = runBlocking {
        // Mock the searchCompanyListing function
        `when`(stockDao.searchCompanyListing("SYM"))
            .thenReturn(listOf(
                CompanyListingEntity("Company Name 1", "SYM", "NYSE"),
                CompanyListingEntity("Company Name 2", "SYM", "NASDAQ")
            ))

        // Call the function that searches for company listings
        val result = stockDao.searchCompanyListing("SYM")

        // Verify that the searchCompanyListing function was called with the expected query
        verify(stockDao).searchCompanyListing("SYM")

        // Verify that the result contains the expected company listings
        assertEquals(2, result.size)
        assertEquals("Company Name 1", result[0].name)
        assertEquals("Company Name 2", result[1].name)
    }
}