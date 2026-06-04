package com.shd.mapbuilder.ai

import kotlinx.serialization.Serializable

@Serializable
data class TileCommand(val x: Int, val y: Int, val z: Int, val typeId: Int, val matId: Int)
