package com.stock.market.parser

import com.opencsv.CSVReader
import com.stock.market.data.csv.IntradayInfoParser
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import java.io.InputStream
import java.io.InputStreamReader
import java.util.zip.ZipInputStream

class IntradayInfoParserTest {

    private lateinit var intradayInfoParser: IntradayInfoParser

    @Before
    fun setUp() {
        intradayInfoParser = IntradayInfoParser()
    }

    @Test
    fun testParse() {
        // Mock dependencies
        val inputStream = mock(InputStream::class.java)
        val zipInputStream = ZipInputStream(inputStream)
        val csvReader = CSVReader(InputStreamReader(zipInputStream))

        // Define CSV content for testing
        val csvContent = listOf(
            arrayOf("Timestamp", "Column1", "Column2", "Column3", "Close"),
            arrayOf("2023-09-23 10:00:00", "Value1", "Value2", "Value3", "100.0"),
            arrayOf("2023-09-23 11:00:00", "Value1", "Value2", "Value3", "110.0"),
            arrayOf("2023-09-23 12:00:00", "Value1", "Value2", "Value3", "120.0"),
            arrayOf("2023-09-24 10:00:00", "Value1", "Value2", "Value3", "90.0")
            // Add more rows as needed
        )

        // Configure behavior of mocks
        `when`(inputStream.readBytes()).thenReturn(byteArrayOf())
        `when`(zipInputStream.nextEntry).thenReturn(null)
        `when`(csvReader.readAll()).thenReturn(csvContent)
        `when`(csvReader.close()).then { /* Do nothing */ }

        // Call the parse() function and capture the result using runBlocking
        val result = runBlocking(Dispatchers.IO) {
            intradayInfoParser.parse(inputStream)
        }

        // Verify the result
        assertEquals(3, result.size) // Assuming three valid entries based on the filter condition

        // Optionally, verify other expectations
        verify(inputStream).readBytes()
        verify(zipInputStream).nextEntry
        verify(csvReader).readAll()
        verify(csvReader).close()

        // Optionally, use verifyNoMoreInteractions to ensure there were no other unexpected interactions
        verifyNoMoreInteractions(inputStream, zipInputStream, csvReader)
    }
}