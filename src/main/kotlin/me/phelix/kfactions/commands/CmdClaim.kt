package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Config
import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

class CmdClaim : SubCommand(arrayOf("claim"), "[x] [z]", "Claim a chunk for your faction", true, Permission.CLAIMING) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if(it.args.isEmpty()) {

                val location = FLocation(it.fme.getPlayer()!!.location)

                if(it.chunkHandler.isClaimed(location) && it.chunkHandler.getFactionFromChunk(location) == it.myFaction) {
                    it.fme.sendMessage(Message.chunkClaimedBySelfFaction, location.x, location.z)
                } else if(it.chunkHandler.isClaimed(location)){
                    it.fme.sendMessage(Message.chunkClaimedByEnemy, location.x, location.z, it.chunkHandler.getFactionFromChunk(location).name)
                } else if(it.myFaction.getPowerLeft() - Config.factionClaimPower < 0){
                    it.fme.sendMessage(Message.chunkClaimPowerShortness)
                } else {
                    it.chunkHandler.claimChunk(location, it.myFaction)
                    it.fme.sendMessage(Message.chunkClaimed, location.x, location.z)
                    it.myFaction.broadCast(Message.chunkClaimBroadcast, location.x, location.z, it.fme.getName())
                }

            } else {
                it.fme.sendMessage(toString())
            }

        }
    }

}