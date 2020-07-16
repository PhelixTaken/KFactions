package me.phelix.kfactions.commands

import me.phelix.kfactions.Faction
import me.phelix.kfactions.utils.Config
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.Role
import me.phelix.kfactions.utils.permissions.FactionPermission
import java.util.*

class CmdCreate : SubCommand(arrayOf("create"), "<name>", "Create a faction", false) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.size != 1) return@let it.fme.sendMessage(it.fme, toString())!!

            if (it.fme.hasFaction()) return@let it.fme.sendMessage(it.fme, Message.alreadyInFaction)!!

            if (it.args[0].length < Config.factionNameMinLength || it.args[0].length > Config.factionNameMaxLength)
                return@let it.fme.sendMessage(
                    it.fme,
                    Message.factionNameLength,
                    Config.factionNameMinLength,
                    Config.factionNameMaxLength
                )!!

            it.factionHandler.getWilderness().players.remove(it.fme)
            val faction = Faction(it.args[0])
            it.factionHandler.map[it.args[0]] = faction
            it.fme.faction = faction
            it.fme.role = Role.LEADER
            faction.power += Config.factionPowerPerPlayer
            faction.factionPermission = FactionPermission()
            faction.factionPermission.setDefaultPermissions()
            faction.players.add(it.fme)
            it.fme.sendMessage(it.fme, Message.factionCreated, it.args[0])!!
        }
    }

}