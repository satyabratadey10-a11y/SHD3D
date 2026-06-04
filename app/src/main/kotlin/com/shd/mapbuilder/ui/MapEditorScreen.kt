package com.shd.mapbuilder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shd.mapbuilder.viewmodel.MapViewModel

@Composable
fun MapEditorScreen(viewModel: MapViewModel = viewModel()) {
    val showAi by viewModel.showAiPanel.collectAsState()
    val bakeProgress by viewModel.bakeProgress.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0F))) {
        AndroidView(
            factory = { ctx -> MapGLSurfaceView(ctx) },
            modifier = Modifier.fillMaxSize()
        )

        EngineStatsHUD(viewModel, modifier = Modifier.align(Alignment.TopStart).padding(16.dp))
        
        LayerPanel(viewModel, modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp))

        Column(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)) {
            TilePicker(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            ToolbarPanel(viewModel)
        }

        FloatingActionButton(
            onClick = { viewModel.toggleAiPanel() },
            containerColor = Color(0xFF7C3AED),
            modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp)
        ) {
            Icon(Icons.Default.Build, contentDescription = "AI Assist", tint = Color.White)
        }

        if (bakeProgress >= 0f) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0xAA000000)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Baking lightmap...", color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(progress = bakeProgress, color = Color(0xFF7C3AED))
                }
            }
        }

        if (showAi) {
            AiAssistPanel(viewModel, onDismiss = { viewModel.toggleAiPanel() })
        }
    }
}
