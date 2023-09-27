package com.stock.market

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main application class for the Stock Market Android application.
 *
 * Annotating the class with [@HiltAndroidApp] indicates that Hilt should generate the necessary
 * Dagger/Hilt components for dependency injection within the application.
 *
 * This class extends [Application], which is the base class for maintaining global application state.
 */
@HiltAndroidApp
class StockApplication : Application()