package me.phelix.kfactions

import me.phelix.kfactions.utils.Role
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class FPlayer(val id: String) {

    @Transient lateinit var faction: Faction
    lateinit var role: Role

    fun getUUID(): UUID = UUID.fromString(id)

    fun getPlayer(): Player? = Bukkit.getPlayer(UUID.fromString(id))

    fun getName(): String = Bukkit.getOfflinePlayer(id).name!!

}