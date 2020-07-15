package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission
import org.bukkit.ChatColor
import java.util.*

abstract class SubCommand(
    val aliases: Array<out String>,
    val usage: String,
    val description: String,
    val factionNeeded: Boolean,
    vararg val permission: Permission
) {

    abstract fun execute(commandContext: CommandContext)

    fun hasPermission(fme: FPlayer): Boolean {
        if (factionNeeded && !fme.hasFaction()) return false
        if (permission.isEmpty()) return true

        permission.forEach {
            if (!fme.faction.factionPermission.hasPermission(fme.role, it)) return false
        }

        return true
    }

    fun sendMessage(fme: FPlayer, message: String) = fme.sendMessage(color("${Message.prefix} $message"))
    fun sendMessage(fme: FPlayer, message: String, vararg objects: Objects) = fme.sendMessage(String.format(color("${Message.prefix} $message"), objects))

    fun color(message: String): String = ChatColor.translateAlternateColorCodes('&', message)

    override fun toString(): String = String.format("/f %s %s", aliases.joinToString(" | "), usage);


}