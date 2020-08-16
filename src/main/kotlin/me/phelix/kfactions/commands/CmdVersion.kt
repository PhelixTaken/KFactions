package me.phelix.kfactions.commands

class CmdVersion : SubCommand(arrayOf("version", "v", "about"), "", "Shows the faction plugins version", false) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {
            it.fme.sendMessage("&7Faction Version: &6${it.plugin.description.version}")
        }
    }

}