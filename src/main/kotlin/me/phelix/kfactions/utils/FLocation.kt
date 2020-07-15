package me.phelix.kfactions.utils

import org.bukkit.Chunk
import org.bukkit.Location

data class FLocation(val x: Int, val z: Int, val id: String) {

    constructor(location: Location): this(location.chunk.x, location.chunk.z, location.world.uid.toString())
    constructor(chunk: Chunk): this(chunk.x, chunk.z, chunk.world.uid.toString())

}