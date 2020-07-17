package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.Role
import me.phelix.kfactions.utils.permissions.Permission

class CmdDefaultRole : SubCommand(
    arrayOf("defaultrole", "dr"),
    "<role>",
    "Sets the default role on joining",
    true,
    Permission.DEFAULT_ROLE
) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            if(it.args.isEmpty() || it.args.size != 1) {
                it.fme.sendMessage(toString())
            } else {
                
                val role = Role.values().find { role ->  it.args[0].toUpperCase() in role.name } ?: return@let it.fme.sendMessage(
                    Message.roleDoesNotExist, it.args[0])

                it.myFaction.defaultRole = role
                it.fme.sendMessage(Message.defaultRoleSet, role.prefix)
            }
        }
    }

}