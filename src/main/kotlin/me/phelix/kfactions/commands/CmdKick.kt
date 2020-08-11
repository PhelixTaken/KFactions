package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission

class CmdKick : SubCommand(arrayOf("kick"), "<player>", "Kick a player from the faction", true, Permission.KICK) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.size == 1) {

                val target = it.playerHandler.getPlayer(it.args[0]) ?: return@let it.fme.sendMessage(
                    Message.playerNotExist,
                    it.args[0]
                )

                if (target.faction.equals(it.myFaction)) {
                    it.myFaction.removePlayer(target)
                    target.faction = it.factionHandler.getWilderness()
                    it.factionHandler.getWilderness().addPlayer(target)
                    it.fme.sendMessage(Message.playerKicked, target.getName())
                    target.sendMessage(Message.targetKicked, it.fme.getName())
                    it.myFaction.broadCast(Message.broadcastKick, target.getName(), it.fme.getName())
                } else {
                    it.fme.sendMessage(Message.factionNotSame, target.getName())
                }

            } else {
                it.fme.sendMessage(toString())
            }
        }
    }

}