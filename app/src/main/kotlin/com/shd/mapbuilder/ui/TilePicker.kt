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

val TilesList = listOf(
    Pair(0, "EMPTY"), Pair(1, "FLOOR"), Pair(2, "WALL"), Pair(3, "RAMP"),
    Pair(4, "WATER"), Pair(5, "DOOR"), Pair(6, "CEILING"), Pair(7, "PILLAR"),
    Pair(8, "STAIRS"), Pair(9, "LAVA")
)

@Composable
fun TilePicker(viewModel: MapViewModel) {
    val selectedType by viewModel.selectedTileType.collectAsState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(TilesList) { tile ->
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .border(
                        width = if (selectedType == tile.first) 2.dp else 0.dp,
                        color = if (selectedType == tile.first) Color(0xFF7C3AED) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { viewModel.setTileType(tile.first) }
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Text(text = tile.second, color = Color.White)
            }
        }
    }
}
