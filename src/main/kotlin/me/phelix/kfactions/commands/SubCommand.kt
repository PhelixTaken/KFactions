package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

abstract class SubCommand(
    val aliases: Array<out String>,
    val usage: String,
    val description: String,
    val factionNeeded: Boolean,
    vararg val permission: Permission
) {

    abstract fun execute(commandContext: CommandContext)

    fun hasPermission(fme: FPlayer): Boolean {
        if (permission.isEmpty()) return true

        permission.forEach {
            if (!fme.faction.factionPermission.hasPermission(fme.role, it)) {
                val permissions = permission.joinToString(", ").toLowerCase()
                fme.sendMessage(Message.playerNoPermission, permissions)
                return false
            }
        }

        return true
    }

    override fun toString(): String = String.format("/f %s %s", aliases.joinToString(" | "), usage);


}