package me.phelix.kfactions.utils

import me.phelix.kfactions.utils.permissions.Permission
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*

class Warp(val name: String, val password: String?, val worldId: UUID, val x: Double, val y: Double, val z: Double) {

    fun hasPassword(): Boolean = password == null

    fun getLocation(): Location = Location(Bukkit.getWorld(worldId), x, y, z)

}