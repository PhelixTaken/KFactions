package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.utils.Message

class CmdJoin : SubCommand(arrayOf("join"), "<faction>", "Join a faction", false) {


    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.size == 1) {

                val faction = it.factionHandler.getFaction(it.args[0]) ?: return@let it.fme.sendMessage(
                    Message.factionNotExist,
                    it.args[0]
                )

                if(faction.bans.contains(it.fme.id))
                    return@let it.fme.sendMessage(Message.factionJoinBan, faction.name)


                if (!faction.open) {

                    //If faction is closed and the player has an invite
                    if (faction.invites.contains(it.fme)) {
                        faction.addPlayer(it.fme)
                        it.fme.sendMessage(Message.factionJoined, it.args[0])
                        faction.broadCast(Message.factionJoinBroadcast, it.fme.getName())

                    }
                    //If the faction is closed and the player has no invite
                    else {
                        return@let it.fme.sendMessage(Message.factionClosedNotInvited, it.args[0])
                    }
                    //If the faction is open
                } else {
                    faction.addPlayer(it.fme)
                    it.fme.sendMessage(Message.factionJoined, it.args[0])
                    faction.broadCast(Message.factionJoinBroadcast, it.fme.getName())
                }

            } else {
                it.fme.sendMessage(toString())
            }
        }
    }

}