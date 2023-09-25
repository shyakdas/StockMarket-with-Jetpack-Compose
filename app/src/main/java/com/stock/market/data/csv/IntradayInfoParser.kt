package com.stock.market.data.csv

import com.opencsv.CSVReader
import com.stock.market.data.mapper.toIntradayInfo
import com.stock.market.data.remote.dto.IntradayInfoDto
import com.stock.market.domain.model.IntradayInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class represents an implementation of the CSVParser interface specifically
 * designed for parsing IntradayInfo data. It is annotated as @Singleton, indicating
 * that only a single instance of this class should be created and shared within the
 * application.
 *
 * @constructor Injects any required dependencies into the class.
 */
@Singleton
class IntradayInfoParser @Inject constructor() : CSVParser<IntradayInfo> {

    /**
     * This function overrides the `parse` function from the CSVParser interface and
     * is responsible for parsing IntradayInfo data from the provided InputStream.
     *
     * @param stream The InputStream from which CSV data will be read and parsed.
     * @return A list of IntradayInfo objects representing the parsed CSV data.
     */
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        // Create a CSV reader for the input stream
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            // Read all CSV rows, skip the header row, and process the data
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    // Extract timestamp and close value from the CSV line
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(timestamp, close.toDouble())
                    dto.toIntradayInfo()
                }.filter {
                    // Filter the IntradayInfo objects based on a specific condition
                    // TODO Better Approach
                    it.date.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
                }.sortedBy {
                    // Sort the IntradayInfo objects by the hour of the date
                    it.date.hour
                }
                .also {
                    // Close the CSV reader to release resources
                    csvReader.close()
                }
        }
    }
}