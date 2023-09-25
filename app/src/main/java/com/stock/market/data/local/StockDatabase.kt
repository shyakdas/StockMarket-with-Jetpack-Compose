package com.stock.market.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * This is the Room Database class for managing CompanyListingEntity objects. It is
 * annotated with @Database to specify the entities it manages and the database version.
 */
@Database(entities = [CompanyListingEntity::class], version = 1)
abstract class StockDatabase : RoomDatabase() {

    /**
     * Provides access to the Data Access Object (DAO) for interacting with
     * CompanyListingEntity objects in the database.
     */
    abstract val dao: StockDao
}