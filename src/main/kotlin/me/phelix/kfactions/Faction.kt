package me.phelix.kfactions

import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.Role
import me.phelix.kfactions.utils.permissions.FactionPermission

class Faction(val name: String) {

    @Transient val claims = mutableSetOf<FLocation>()
    val players = mutableSetOf<FPlayer>()
    val allies = mutableSetOf<String>()
    val enemies = mutableSetOf<String>()
    lateinit var factionPermission: FactionPermission
    var description = "Default faction description"
    var power: Int = 0
    var open = false
    var defaultRole = Role.RECRUIT


}