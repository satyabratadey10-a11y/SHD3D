package com.shd.mapbuilder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shd.mapbuilder.ai.GeminiMapAgent
import com.shd.mapbuilder.ai.TileCommand
import com.shd.mapbuilder.data.MapProject
import com.shd.mapbuilder.jni.MapEngineJNI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class Tool { PLACE, DELETE, SELECT, PAINT, FILL }

class MapViewModel : ViewModel() {
    private val agent = GeminiMapAgent()

    private val _selectedTool = MutableStateFlow(Tool.PLACE)
    val selectedTool: StateFlow<Tool> = _selectedTool
    
    private val _selectedTileType = MutableStateFlow(1)
    val selectedTileType: StateFlow<Int> = _selectedTileType
    
    private val _selectedMaterial = MutableStateFlow(0)
    val selectedMaterial: StateFlow<Int> = _selectedMaterial

    private val _currentLayer = MutableStateFlow(0)
    val currentLayer: StateFlow<Int> = _currentLayer

    private val _mapDepth = MutableStateFlow(10)
    val mapDepth: StateFlow<Int> = _mapDepth

    private val _bakeProgress = MutableStateFlow(-1f)
    val bakeProgress: StateFlow<Float> = _bakeProgress

    private val _fps = MutableStateFlow(0f)
    val fps: StateFlow<Float> = _fps

    private val _drawCalls = MutableStateFlow(0)
    val drawCalls: StateFlow<Int> = _drawCalls

    private val _vramMB = MutableStateFlow(0f)
    val vramMB: StateFlow<Float> = _vramMB
    
    private val _showAiPanel = MutableStateFlow(false)
    val showAiPanel: StateFlow<Boolean> = _showAiPanel
    
    private val _aiResponse = MutableStateFlow("")
    val aiResponse: StateFlow<String> = _aiResponse
    
    private val _savedMaps = MutableStateFlow<List<MapProject>>(emptyList())
    val savedMaps: StateFlow<List<MapProject>> = _savedMaps

    init {
        viewModelScope.launch {
            while (true) {
                delay(500)
                try {
                    _fps.value = MapEngineJNI.nativeGetFPS()
                    _drawCalls.value = MapEngineJNI.nativeGetDrawCalls()
                    _vramMB.value = MapEngineJNI.nativeGetVramMB()
                    _bakeProgress.value = MapEngineJNI.nativeBakeProgress()
                } catch (e: Exception) {}
            }
        }
    }

    fun setTool(tool: Tool) { _selectedTool.value = tool }
    fun setTileType(type: Int) { _selectedTileType.value = type }
    fun setMaterial(mat: Int) { _selectedMaterial.value = mat }
    fun setLayer(z: Int) { _currentLayer.value = z }
    fun toggleAiPanel() { _showAiPanel.value = !_showAiPanel.value }

    fun sendAiPrompt(prompt: String) {
        viewModelScope.launch {
            _aiResponse.value = "Generating map..."
            val commands = agent.generateMap(prompt, 20, 20, 10)
            if (commands.isNotEmpty()) {
                val jsonStr = Json.encodeToString(commands)
                try {
                    MapEngineJNI.nativeLoadTilesBatch(jsonStr)
                    _aiResponse.value = "Generated ${commands.size} tiles successfully!"
                } catch (e: Exception) {
                    _aiResponse.value = "Engine error linking generated tiles."
                }
            } else {
                _aiResponse.value = "Generation failed or returned 0 tiles."
            }
        }
    }
}
