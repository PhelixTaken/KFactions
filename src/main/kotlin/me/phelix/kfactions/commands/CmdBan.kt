package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message

class CmdBan : SubCommand(arrayOf("ban"), "<player>", "Ban a player from the faction", true) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            if (it.args.size == 1) {

                val target = it.playerHandler.getPlayer(it.args[0]) ?: return@let it.fme.sendMessage(
                    Message.playerNotExist,
                    it.args[0]
                )

                if (target.faction == it.myFaction) {
                    it.myFaction.removePlayer(target)
                    it.myFaction.bans.add(target.id)
                    target.faction = it.factionHandler.getWilderness()
                    it.factionHandler.getWilderness().addPlayer(target)
                    it.fme.sendMessage(Message.playerBan, target.getName())
                    target.sendMessage(Message.targetBan, it.fme.getName())
                    it.myFaction.broadCast(Message.broadcastBan, target.getName(), it.fme.getName())
                } else {
                    it.fme.sendMessage(Message.factionNotSame, target.getName())
                }

            } else {
                it.fme.sendMessage(toString())
            }
        }
    }

}