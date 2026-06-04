package com.shd.mapbuilder.ai

object MapPromptBuilder {
    fun build(prompt: String): String {
        return "Create a 3D voxel map with tiles based on description: $prompt. Enclose output in standard JSON array only."
    }
}
