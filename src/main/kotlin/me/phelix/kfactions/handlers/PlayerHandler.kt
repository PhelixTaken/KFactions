package me.phelix.kfactions.handlers

import me.phelix.kfactions.FPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PlayerHandler {

    lateinit var map: MutableMap<String, FPlayer>

    fun getPlayer(player: Player): FPlayer? = map[player.uniqueId.toString()]

    fun getPlayer(name: String): FPlayer? = map[Bukkit.getPlayer(name)?.uniqueId.toString()]

}