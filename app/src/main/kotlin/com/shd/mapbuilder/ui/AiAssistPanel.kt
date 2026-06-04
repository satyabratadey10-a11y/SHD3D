package com.shd.mapbuilder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shd.mapbuilder.viewmodel.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiAssistPanel(viewModel: MapViewModel, onDismiss: () -> Unit) {
    var prompt by remember { mutableStateOf("") }
    val response by viewModel.aiResponse.collectAsState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF0A0A0F)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text("Gemini AI Map Generation", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = prompt,
                onValueChange = { prompt = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Describe your map...", color = Color.Gray) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.sendAiPrompt(prompt) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C3AED)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Map")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (response.isNotEmpty()) {
                Text(response, color = Color(0xFFE0E0E0))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
