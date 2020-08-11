package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CmdDisband : SubCommand(arrayOf("disband"), "[confirm]", "Disband your faction", true, Permission.DISBAND) {

    private val map = mutableSetOf<FPlayer>()

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.isEmpty()) {

                if (!map.contains(it.fme)) {
                    it.fme.sendMessage(Message.disbandConfirmMessage)
                    map.add(it.fme)
                } else {
                    return@let it.fme.sendMessage(Message.disbandAlreadyConfirm)
                }

            } else {
                if (!map.contains(it.fme)) {
                    return@let it.fme.sendMessage(Message.disbandNotConfirmed)
                } else {
                    it.fme.sendMessage(Message.disbandSuccessfully, it.myFaction.name)
                    it.myFaction.broadCast(Message.disbandBroadcast, it.myFaction.name, it.fme.getName())
                    it.myFaction.players.forEach { player ->
                        it.myFaction.removePlayer(player)
                        player.faction = it.factionHandler.getWilderness()
                        it.factionHandler.getWilderness().addPlayer(player)
                    }
                    it.factionHandler.map.remove(it.myFaction.name)
                }
            }

        }
    }

//    fun <E> MutableSet<E>.removeAfter(element: E, seconds: Long) {
//        this.add(element)
//        Executors.newSingleThreadScheduledExecutor().schedule({
//            this.removeIf { element in this}
//        }, seconds, TimeUnit.SECONDS)
//    }

}