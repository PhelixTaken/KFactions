package me.phelix.kfactions.commands

abstract class SubCommand(val aliases: Array<out String>, val usage: String, val description: String, val factionNeeded: Boolean) {

    abstract fun execute(commandContext: CommandContext)

    fun hasPermission(): Boolean {
        return true
    }

    fun sendMessage() = null

}