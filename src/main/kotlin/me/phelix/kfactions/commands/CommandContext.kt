package me.phelix.kfactions.commands

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.Faction
import me.phelix.kfactions.KFactions
import me.phelix.kfactions.handlers.ChunkHandler
import me.phelix.kfactions.handlers.FactionHandler
import me.phelix.kfactions.handlers.PlayerHandler

data class CommandContext(
    val plugin: KFactions,
    val playerHandler: PlayerHandler,
    val factionHandler: FactionHandler,
    val chunkHandler: ChunkHandler,
    val fme: FPlayer,
    val myFaction: Faction,
    val args: Array<out String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandContext

        if (plugin != other.plugin) return false
        if (playerHandler != other.playerHandler) return false
        if (factionHandler != other.factionHandler) return false
        if (chunkHandler != other.chunkHandler) return false
        if (fme != other.fme) return false
        if (myFaction != other.myFaction) return false
        if (!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = plugin.hashCode()
        result = 31 * result + playerHandler.hashCode()
        result = 31 * result + factionHandler.hashCode()
        result = 31 * result + chunkHandler.hashCode()
        result = 31 * result + fme.hashCode()
        result = 31 * result + myFaction.hashCode()
        result = 31 * result + args.contentHashCode()
        return result
    }
}