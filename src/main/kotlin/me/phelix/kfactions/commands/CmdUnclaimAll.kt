package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

class CmdUnclaimAll : SubCommand(arrayOf("unclaimall"), "[confirm]", "Unclaim all the chunks from your faction", true, Permission.UNCLAIMING_ALL) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            if (it.args.isEmpty()) {

                if(it.myFaction.claims.size == 0) {
                    return@let it.fme.sendMessage(Message.unclaimAllNone)
                }

                it.myFaction.claims.forEach { fLocation ->
                    it.chunkHandler.unclaimChunk(fLocation)
                }
                it.fme.sendMessage(Message.unclaimAll)
                it.myFaction.broadCast(Message.unclaimAllBroadcast, it.fme.getName())

            } else {
                it.fme.sendMessage(toString())
            }

        }
    }

}