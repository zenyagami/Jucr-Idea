package com.zenkun.jucr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zenkun.jucr.ui.compose.HomeScreen
import com.zenkun.jucr.ui.theme.JucrAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JucrAppTheme {
                HomeScreen()
            }
        }
    }
}
