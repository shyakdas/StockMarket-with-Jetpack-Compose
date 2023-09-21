package com.stock.market.data.local

import androidx.room.Database

@Database(entities = [CompanyListingEntity::class], version = 1)
abstract class StockDatabase {

    abstract val dao: StockDao
}