package com.stock.market.data.csv

import com.opencsv.CSVReader
import com.stock.market.domain.model.CompanyListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class represents an implementation of the CSVParser interface specifically
 * designed for parsing CompanyListing data. It is annotated as @Singleton, indicating
 * that only a single instance of this class should be created and shared within the
 * application.
 *
 * @constructor Injects any required dependencies into the class.
 */
@Singleton
class CompanyListingsParser @Inject constructor(): CSVParser<CompanyListing> {

    /**
     * This function overrides the `parse` function from the CSVParser interface and
     * is responsible for parsing CompanyListing data from the provided InputStream.
     *
     * @param stream The InputStream from which CSV data will be read and parsed.
     * @return A list of CompanyListing objects representing the parsed CSV data.
     */
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        // Create a CSV reader for the input stream
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            // Read all CSV rows, skip the header row, and process the data
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    // Extract symbol, name, and exchange from the CSV line
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    // Create a CompanyListing object from the extracted data
                    CompanyListing(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    // Close the CSV reader to release resources
                    csvReader.close()
                }
        }
    }
}