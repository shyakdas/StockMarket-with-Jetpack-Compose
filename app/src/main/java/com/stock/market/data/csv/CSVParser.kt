package com.stock.market.data.csv

import java.io.InputStream

/**
 * This is the interface for a CSV (Comma-Separated Values) parser, which is used to
 * define a contract for parsing CSV data into Java objects of type T.
 *
 * Implementations of this interface will provide methods for reading CSV data from
 * various sources, such as files or strings, and mapping the data to instances of
 * the specified type T.
 *
 * @param <T> The type of Java objects to which CSV data will be parsed.
 */
interface CSVParser<T> {

    /**
     * This suspending function is responsible for parsing CSV data from an InputStream
     * and converting it into a list of objects of type T.
     *
     * @param stream The InputStream from which CSV data will be read and parsed.
     * @return A list of objects of type T representing the parsed CSV data.
     */
    suspend fun parse(stream: InputStream): List<T>
}