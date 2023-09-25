package com.stock.market.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This is the DAO (Data Access Object) interface for managing the CompanyListingEntity
 * objects in a database. It provides methods for inserting, clearing, and searching
 * company listings.
 */
@Dao
interface StockDao {

    /**
     * Inserts a list of CompanyListingEntity objects into the database, with
     * conflict resolution strategy set to replace existing entries.
     *
     * @param companyListingEntity The list of company listings to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(companyListingEntity: List<CompanyListingEntity>)

    /**
     * Clears all records from the "companylistingentity" table in the database.
     */
    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListing()

    /**
     * Searches for company listings in the database based on a query string. The
     * query can match either the name (case-insensitive) or the symbol (case-sensitive).
     *
     * @param query The query string to search for.
     * @return A list of matching CompanyListingEntity objects.
     */
    @Query(""" SELECT * FROM companylistingentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol""")
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}