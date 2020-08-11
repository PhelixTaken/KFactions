package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.colorize
import me.phelix.kfactions.utils.Config
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.Role.*

class CmdWho : SubCommand(arrayOf("who", "show"), "[faction]", "Shows the faction information", false) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            when (it.args.size) {
                0 -> {
                    if (it.fme.faction.name.equals("Wilderness", ignoreCase = true))
                        it.fme.sendMessage(Message.factionNeeded)
                    else {
                        showInformation(it.fme, it.fme)
                    }
                }
                1 -> {
                    val target: FPlayer =
                        if (it.playerHandler.getPlayer(it.args[0]) == null) return it.fme.sendMessage(
                            Message.playerNotExist,
                            it.args[0]
                        )!! else it.playerHandler.getPlayer(it.args[0])!!

                    showInformation(target, it.fme)
                }
                else -> {
                    it.fme.sendMessage(toString())
                }
            }
        }
    }

    private fun showInformation(player: FPlayer, sender: FPlayer) {
        val faction = player.faction
        val list = mutableListOf<String>()

        val players = mutableSetOf<String>()
        faction.players.forEach {
            var name = ""
            when (it.role) {
                MODERATOR -> name += Config.moderatorPrefix
                COLEADER -> name += Config.coleaderPrefix
                LEADER -> name += Config.leaderPrefix
            }
            name += it.getName()
            players.add(name)
        }

        val totalClaims = (faction.getTotalPower() / Config.factionClaimPower).toString()
        val allies = faction.allies.toString().replace("[", "").replace("]", "")
        val enemies = faction.enemies.toString().replace("[", "").replace("]", "")
        val playerNames = players.toString().replace("[", "").replace("]", "")

        Message.show.forEach {
            val string = it
                .replace("{faction_name}", faction.name)
                .replace("{faction_description}", faction.description)
                .replace("{faction_leader}", faction.getLeader().getName())
                .replace("{faction_members}", playerNames)
                .replace("{faction_claims}", faction.claims.size.toString())
                .replace("{faction_totalclaims}", totalClaims)
                .replace("{faction_powerleft}", faction.getPowerLeft().toString())
                .replace("{faction_totalpower}", faction.getTotalPower().toString())
                .replace("{faction_allies}", allies)
                .replace("{faction_enemies}", enemies)
            list.add(string)
        }

        list.forEach {
            sender.sendDefaultMessage(it.colorize())
        }
    }


}