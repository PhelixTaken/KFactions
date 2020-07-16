package me.phelix.kfactions

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.Role
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

class FPlayer(val id: String) {

    @Transient lateinit var faction: Faction
    lateinit var role: Role

    fun getUUID(): UUID = UUID.fromString(id)

    fun getPlayer(): Player? = Bukkit.getPlayer(UUID.fromString(id))

    fun getName(): String = Bukkit.getOfflinePlayer(id).name!!

    fun hasFaction(): Boolean = faction.name != "Wilderness"

    fun sendMessage(message: String) = getPlayer()?.sendMessage(message)

    fun sendMessage(fme: FPlayer, message: String) = fme.sendMessage("${Message.prefix} $message".colorize())
    fun sendMessage(fme: FPlayer, message: String, vararg objects: Any) = fme.sendMessage("${Message.prefix} $message".colorize().format(*objects))

}

fun String.colorize() = ChatColor.translateAlternateColorCodes('&', this)
