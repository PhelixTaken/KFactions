package me.phelix.kfactions.commands

import me.phelix.kfactions.KFactions
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHandler(private val plugin: KFactions) : CommandExecutor {

    private val commands = mutableSetOf(CmdCreate())

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}Only players can execute this command!")
            return true
        }

        if (cmd.name.equals("f", ignoreCase = true)) {
            if (args.isNotEmpty()) {
                val subCommand = commands.find { args[0] in it.aliases } ?: return true
                subCommand.execute(
                    CommandContext(
                        plugin,
                        plugin.playerHandler,
                        plugin.factionHandler,
                        plugin.chunkHandler,
                        plugin.playerHandler.getPlayer(sender)!!,
                        plugin.playerHandler.getPlayer(sender)!!.faction,
                        args.copyOfRange(1, args.size)
                    )
                )
            }
        }
        return true
    }

}