package me.phelix.kfactions.handlers

import me.phelix.kfactions.FPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PlayerHandler {

    var map: MutableMap<String, FPlayer> = mutableMapOf()

    fun getPlayer(player: Player): FPlayer? = map[player.uniqueId.toString()]

    fun getPlayer(name: String): FPlayer? = map[Bukkit.getPlayer(name)?.uniqueId.toString()]

}