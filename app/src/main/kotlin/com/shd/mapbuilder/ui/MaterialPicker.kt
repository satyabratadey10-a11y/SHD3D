package com.shd.mapbuilder.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shd.mapbuilder.viewmodel.MapViewModel

val MaterialsList = listOf(
    Pair(0, "STONE"), Pair(1, "WOOD"), Pair(2, "BRICK"), Pair(3, "MARBLE"),
    Pair(4, "METAL"), Pair(5, "DIRT"), Pair(6, "GRASS"), Pair(7, "SAND"),
    Pair(8, "ICE"), Pair(9, "OBSIDIAN")
)

@Composable
fun MaterialPicker(viewModel: MapViewModel) {
    val selectedMat by viewModel.selectedMaterial.collectAsState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(MaterialsList) { mat ->
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .border(
                        width = if (selectedMat == mat.first) 2.dp else 0.dp,
                        color = if (selectedMat == mat.first) Color(0xFF7C3AED) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { viewModel.setMaterial(mat.first) }
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Text(text = mat.second, color = Color.White)
            }
        }
    }
}
