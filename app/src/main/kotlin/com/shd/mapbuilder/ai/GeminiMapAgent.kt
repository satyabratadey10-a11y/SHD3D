package com.shd.mapbuilder.ai

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.shd.mapbuilder.BuildConfig

class GeminiMapAgent {
    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_KEY,
        generationConfig = generationConfig {
            responseMimeType = "application/json"
            temperature = 0.4f
        }
    )

    suspend fun generateMap(prompt: String, w: Int, h: Int, d: Int): List<TileCommand> {
        if (BuildConfig.GEMINI_KEY.isEmpty()) return emptyList()
        val sysPrompt = "Return a JSON array of tile combinations [{'x':0,'y':0,'z':0,'typeId':1,'matId':1}] for the requested map: $prompt"
        return try {
            val response = model.generateContent(sysPrompt)
            MapCommandParser.parse(response.text ?: "[]")
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun suggestFill(surroundingTiles: String, prompt: String): List<TileCommand> {
        return generateMap(prompt, 10, 10, 5)
    }
}
