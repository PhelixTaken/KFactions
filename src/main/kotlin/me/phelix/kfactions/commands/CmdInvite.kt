package me.phelix.kfactions.commands

import me.phelix.kfactions.colorize
import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.permissions.Permission
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit

class CmdInvite : SubCommand(arrayOf("invite"), "<player>", "Invite a player to the faction", true, Permission.INVITING) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if(it.args.size == 1) {

                val targetPlayer = Bukkit.getPlayer(it.args[0]) ?: return@let it.fme.sendMessage(Message.playerNotExist, it.args[0])

                val target = it.playerHandler.getPlayer(targetPlayer)

                if(it.myFaction.bans.contains(target!!.id))
                    return@let it.fme.sendMessage(Message.playerInviteBan)


                if(it.myFaction.players.contains(target))
                    return@let it.fme.sendMessage(Message.playerAlreadyInSameFaction, it.args[0])

                if(target.hasFaction())
                    return@let it.fme.sendMessage(Message.playerIsAlreadyInFaction, it.args[0])

                val component = TextComponent()
                component.text = "${Message.prefix.colorize()} ${Message.playerNotificationInvited.format(it.fme.getName(), it.myFaction.name).colorize()}"
                component.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f join ${it.myFaction.name}")
                it.myFaction.invites.add(target)
                target.getPlayer()!!.spigot().sendMessage(component)
                it.fme.sendMessage(Message.playerInvited, it.args[0])
            } else {
                it.fme.sendMessage(toString())
            }

        }
    }
}