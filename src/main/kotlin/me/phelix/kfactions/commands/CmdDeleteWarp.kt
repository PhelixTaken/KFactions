package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

class CmdDeleteWarp : SubCommand(arrayOf("deletewarp", "delwarp", "dw"), "<warp> [passowrd]", "Delete an existing warp from your faction", true, Permission.DELETE_WARP) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if(it.args.size in 1..2) {

                val name = it.args[0]
                val warp = it.myFaction.getWarp(name) ?: return@let it.fme.sendMessage(Message.deleteWarpNotExist, name)

                if(it.args.size == 1 && warp.hasPassword())
                    it.fme.sendMessage(Message.deleteWarpPasswordNeeded, name)
                 else {
                    if (warp.password != it.args[1]) return@let it.fme.sendMessage(Message.deleteWarpPasswordNotMatch, name)
                    it.myFaction.warps.remove(name)
                    it.fme.sendMessage(Message.deleteWarp, name)
                }

            } else {
                it.fme.sendMessage(toString())
            }

        }
    }

}