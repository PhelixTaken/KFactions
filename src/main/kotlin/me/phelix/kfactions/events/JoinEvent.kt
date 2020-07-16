package me.phelix.kfactions.events

import me.phelix.kfactions.FPlayer
import me.phelix.kfactions.KFactions
import me.phelix.kfactions.utils.Role
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinEvent(private val plugin: KFactions) : Listener {


    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val handler = plugin.playerHandler
        if(!handler.map.containsKey(player.uniqueId.toString())) {
            val fme = FPlayer(player.uniqueId.toString())
            handler.map[fme.id] = fme
            plugin.factionHandler.getWilderness().players.add(fme)
            fme.faction = plugin.factionHandler.getWilderness()
            fme.role = Role.RECRUIT
        }
        println(plugin.factionHandler.map.toString())
    }

}