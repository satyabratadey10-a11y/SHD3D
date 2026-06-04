package com.shd.mapbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shd.mapbuilder.ui.MapEditorScreen
import com.shd.mapbuilder.ui.theme.ShdMapBuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShdMapBuilderTheme {
                MapEditorScreen()
            }
        }
    }
}
