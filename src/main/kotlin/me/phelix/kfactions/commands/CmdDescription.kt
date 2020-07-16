package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission
import java.lang.StringBuilder

class CmdDescription : SubCommand(
    arrayOf("description"),
    "<description>",
    "Change the description of the faction",
    true,
    Permission.SET_DESCRIPTION
) {


    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.isEmpty()) return@let it.fme.sendMessage(toString())!!

            val builder = StringBuilder()
            it.args.forEach { message ->
                builder.append(message).append(" ")
            }

            it.myFaction.description = builder.toString()
            it.fme.sendMessage(Message.descriptionChanged, builder.toString())
        }
    }

}