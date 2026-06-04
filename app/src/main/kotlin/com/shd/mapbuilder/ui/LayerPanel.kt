package com.shd.mapbuilder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shd.mapbuilder.viewmodel.MapViewModel

@Composable
fun LayerPanel(viewModel: MapViewModel, modifier: Modifier = Modifier) {
    val currentLayer by viewModel.currentLayer.collectAsState()
    val maxDepth by viewModel.mapDepth.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0x14FFFFFF))
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Text("Layer Z: $currentLayer", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = currentLayer.toFloat(),
            onValueChange = { viewModel.setLayer(it.toInt()) },
            valueRange = 0f..(maxDepth - 1).toFloat(),
            steps = maxDepth - 2,
            modifier = Modifier.height(150.dp)
        )
    }
}
