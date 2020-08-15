package me.phelix.kfactions.commands

import me.phelix.kfactions.KFactions
import me.phelix.kfactions.utils.Message
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHandler(private val plugin: KFactions) : CommandExecutor {

    private val commands =
        mutableSetOf(
            CmdCreate(), CmdWho(), CmdDescription(),
            CmdInvite(), CmdDefaultRole(), CmdJoin(), CmdKick(),
            CmdDisband(), CmdClaim(), CmdUnclaim(), CmdUnclaimAll()
        )

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}Only players can execute this command!")
            return true
        }

        if (cmd.name.equals("f", ignoreCase = true)) {
            if (args.isNotEmpty()) {
                val subCommand = commands.find { args[0] in it.aliases } ?: return true
                val player = plugin.playerHandler.getPlayer(sender)!!

                if(subCommand.factionNeeded && !player.hasFaction()) {
                    player.sendMessage(Message.factionNeeded)
                    return true
                }

                if (subCommand.hasPermission(player)) {
                    subCommand.execute(
                        CommandContext(
                            plugin,
                            plugin.playerHandler,
                            plugin.factionHandler,
                            plugin.chunkHandler,
                            player,
                            player.faction,
                            args.copyOfRange(1, args.size)
                        )
                    )
                }
            }
        }
        return true
    }

}