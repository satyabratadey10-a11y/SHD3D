package com.shd.mapbuilder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shd.mapbuilder.viewmodel.MapViewModel

@Composable
fun EngineStatsHUD(viewModel: MapViewModel, modifier: Modifier = Modifier) {
    val fps by viewModel.fps.collectAsState()
    val drawCalls by viewModel.drawCalls.collectAsState()
    val vram by viewModel.vramMB.collectAsState()

    val fpsColor = when {
        fps >= 50f -> Color.Green
        fps >= 30f -> Color.Yellow
        else -> Color.Red
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x14FFFFFF))
            .padding(12.dp)
    ) {
        Text("FPS: %.1f".format(fps), color = fpsColor, fontSize = 14.sp)
        Text("Draw Calls: $drawCalls", color = Color.LightGray, fontSize = 12.sp)
        Text("VRAM: %.1f MB".format(vram), color = Color.LightGray, fontSize = 12.sp)
    }
}
