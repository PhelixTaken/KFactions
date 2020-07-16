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
    lateinit var factionPermission: FactionPermission
    var description = "Default faction description"
    var open = false
    var defaultRole = Role.RECRUIT

    fun getLeader(): FPlayer = players.stream().filter { fPlayer -> fPlayer.role == Role.LEADER }.findFirst().get()

    fun getPowerLeft(): Float = (players.size * Config.factionPowerPerPlayer).toFloat() - (claims.size * Config.factionPowerPerPlayer).toFloat()

    fun getPowerUsed(): Float = (players.size * Config.factionPowerPerPlayer).toFloat() - getPowerLeft()

    fun getTotalPower(): Float = getPowerLeft() + getPowerUsed()

}