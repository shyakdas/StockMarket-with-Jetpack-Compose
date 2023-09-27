package com.stock.market.parser

import com.opencsv.CSVReader
import com.stock.market.data.csv.CompanyListingsParser
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.ByteArrayInputStream
import org.mockito.kotlin.whenever

class CompanyListingsParserTest {

    @Mock
    private lateinit var mockCsvReader: CSVReader

    private lateinit var parser: CompanyListingsParser

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        parser = CompanyListingsParser()
    }

    @Test
    fun testParse() = runBlocking {
        // Prepare a sample CSV data input stream
        val csvData = "Symbol,Name,Exchange\nAAPL,Apple Inc.,NASDAQ\nGOOGL,Alphabet Inc.,NASDAQ"
        val inputStream = ByteArrayInputStream(csvData.toByteArray(Charsets.UTF_8))

        // Mock the behavior of the CSV reader
        whenever(mockCsvReader.readAll()).thenReturn(
            listOf(
                arrayOf("Symbol", "Name", "Exchange"),
                arrayOf("AAPL", "Apple Inc.", "NASDAQ"),
                arrayOf("GOOGL", "Alphabet Inc.", "NASDAQ")
            )
        )

        // Perform the parsing
        val listings = parser.parse(inputStream)

        // Assert the results
        assertEquals(2, listings.size)
        assertEquals("Apple Inc.", listings[0].name)
        assertEquals("AAPL", listings[0].symbol)
        assertEquals("NASDAQ", listings[0].exchange)
        assertEquals("Alphabet Inc.", listings[1].name)
        assertEquals("GOOGL", listings[1].symbol)
        assertEquals("NASDAQ", listings[1].exchange)
    }
}