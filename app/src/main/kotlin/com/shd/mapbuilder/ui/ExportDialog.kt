package com.shd.mapbuilder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shd.mapbuilder.jni.MapEngineJNI

@Composable
fun ExportDialog(onDismiss: () -> Unit) {
    var mapName by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Export / Save Map", color = Color.White) },
        text = {
            Column {
                TextField(
                    value = mapName,
                    onValueChange = { mapName = it },
                    placeholder = { Text("Map Name (for DB save)", color = Color.Gray) }
                )
            }
        },
        confirmButton = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { 
                    MapEngineJNI.nativeExportOBJ()
                    onDismiss()
                }) { Text("Export .OBJ") }
                Button(onClick = { 
                    MapEngineJNI.nativeExportGLTF("/sdcard/Download/$mapName.gltf")
                    onDismiss()
                }) { Text("Export .GLTF") }
                Button(onClick = { 
                    MapEngineJNI.nativeSaveMap()
                    onDismiss()
                }) { Text("Save to Room DB") }
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        },
        containerColor = Color(0xFF0A0A0F)
    )
}
