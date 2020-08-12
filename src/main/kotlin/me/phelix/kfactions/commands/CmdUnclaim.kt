package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

class CmdUnclaim : SubCommand(arrayOf("unclaim"), "[x] [z]", "Unclaim a chunk from your faction", true, Permission.UNCLAIMING) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            if (it.args.isEmpty()) {

                val location = FLocation(it.fme.getPlayer()!!.location)

                if (!it.chunkHandler.isClaimed(location)) {
                    it.fme.sendMessage(Message.unclaimChunkNotClaimed, location.x, location.z)
                } else if (it.chunkHandler.getFactionFromChunk(location) != it.myFaction) {
                    it.fme.sendMessage(Message.unclaimChunkEnemy)
                } else {
                    it.chunkHandler.unclaimChunk(location)
                    it.fme.sendMessage(Message.unclaimChunk, location.x, location.z)
                    it.myFaction.broadCast(Message.unclaimChunkBroadcast, location.x, location.z, it.fme.getName())
                }
            } else {
                it.fme.sendMessage(toString())
            }

        }
    }

}