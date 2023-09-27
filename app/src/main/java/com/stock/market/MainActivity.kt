package com.stock.market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.stock.market.presentation.NavGraphs
import com.stock.market.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main entry point of the Android application.
 *
 * Annotating the class with [@AndroidEntryPoint] allows Hilt to inject dependencies into this activity
 * and enables dependency injection throughout the application.
 *
 * This class extends [ComponentActivity], which is a recommended base class for modern Android activities.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created.
     *
     * This method is part of the Android Activity lifecycle and is invoked when the activity is being
     * created. It is responsible for initializing the user interface and setting up the content view.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}