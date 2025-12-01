package com.areazeroseven.dittohoosports

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.areazeroseven.dittohoosports.feature_scores.presentation.ScoresScreen
import com.areazeroseven.dittohoosports.theme.ZeroSevenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.BLACK)
        )
        setContent {
            ZeroSevenTheme(dynamicColor = false) {
                ScoresScreen()
            }
        }
    }
}
