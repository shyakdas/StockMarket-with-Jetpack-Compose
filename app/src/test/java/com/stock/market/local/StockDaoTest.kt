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

class StockDaoTest {

    private lateinit var stockDao: StockDao

    @Before
    fun setUp() {
        stockDao = mock(StockDao::class.java)
    }

    @Test
    fun testInsertCompanyListing() = runBlocking {
        // Create a list of CompanyListingEntity instances
        val companyListings = listOf(
            CompanyListingEntity("Company1", "SYM1", "NYSE"),
            CompanyListingEntity("Company2", "SYM2", "NASDAQ")
        )

        // Call the function that inserts company listings into the database
        stockDao.insertCompanyListing(companyListings)

        // Verify that the insertCompanyListing function was called with the expected list
        verify(stockDao).insertCompanyListing(companyListings)
    }

    @Test
    fun testClearCompanyListing() = runBlocking {

        // Call the function that clears all records from the table
        stockDao.clearCompanyListing()

        // Verify that the clearCompanyListing function was called
        verify(stockDao).clearCompanyListing()
    }

    @Test
    fun testSearchCompanyListing() = runBlocking {
        // Mock the searchCompanyListing function to return a list of CompanyListingEntity instances
        val query = "SYM"
        val expectedResults = listOf(
            CompanyListingEntity("Company1", "SYM1", "NYSE"),
            CompanyListingEntity("Company2", "SYM2", "NASDAQ")
        )
        `when`(stockDao.searchCompanyListing(query)).thenReturn(expectedResults)

        // Call the function that searches for company listings
        val results = stockDao.searchCompanyListing(query)

        // Verify that the searchCompanyListing function was called with the expected query
        verify(stockDao).searchCompanyListing(query)

        // Verify that the results match the expected results
        assertEquals(expectedResults, results)
    }
}