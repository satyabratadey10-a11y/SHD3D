package com.shd.mapbuilder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.shd.mapbuilder.viewmodel.MapViewModel
import com.shd.mapbuilder.viewmodel.Tool

@Composable
fun ToolbarPanel(viewModel: MapViewModel) {
    val selectedTool by viewModel.selectedTool.collectAsState()
    
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x14FFFFFF))
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Tool.values().forEach { tool ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (selectedTool == tool) Color(0xFF7C3AED) else Color.Transparent)
                    .clickable { viewModel.setTool(tool) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = tool.name, color = Color.White)
            }
        }
    }
}
