package me.phelix.kfactions

import me.phelix.kfactions.utils.Config
import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.Role
import me.phelix.kfactions.utils.permissions.FactionPermission

class Faction(val name: String) {

    @Transient
    var claims = mutableSetOf<FLocation>()
    val players = mutableSetOf<FPlayer>()
    val allies = mutableSetOf<String>()
    val enemies = mutableSetOf<String>()
    val bans = mutableSetOf<String>()

    @Transient
    var invites = mutableSetOf<FPlayer>()
    val factionPermission: FactionPermission = FactionPermission()
    var description = "Default faction description"
    var open = false
    var defaultRole = Role.RECRUIT

    fun addPlayer(player: FPlayer) {
        if(player.faction.name.equals("Wilderness", ignoreCase = true))
            player.faction.removePlayer(player)
        player.faction = this
        players.add(player)
    }

    fun removePlayer(player: FPlayer) = players.remove(player)

    fun getLeader(): FPlayer = players.stream().filter { fPlayer -> fPlayer.role == Role.LEADER }.findFirst().get()

    fun getPowerLeft(): Float =
        (players.size * Config.factionPowerPerPlayer).toFloat() - (claims.size * Config.factionClaimPower).toFloat()

    fun getPowerUsed(): Float = (players.size * Config.factionPowerPerPlayer).toFloat() - getPowerLeft()

    fun getTotalPower(): Float = getPowerLeft() + getPowerUsed()

    fun broadCast(message: String, vararg objects: Any) = players.forEach { if(it.getPlayer() != null) it.sendMessage(message, *objects) }

}