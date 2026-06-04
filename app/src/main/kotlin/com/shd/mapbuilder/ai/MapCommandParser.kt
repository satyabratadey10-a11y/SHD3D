package com.shd.mapbuilder.ai

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

object MapCommandParser {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }
    
    fun parse(jsonStr: String): List<TileCommand> {
        return try {
            json.decodeFromString<List<TileCommand>>(jsonStr)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
