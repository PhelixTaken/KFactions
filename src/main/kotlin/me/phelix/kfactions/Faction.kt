package me.phelix.kfactions

import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.permissions.FactionPermission

class Faction(val name: String) {

    @Transient val claims = mutableSetOf<FLocation>()
    val players = mutableSetOf<FPlayer>()
    val allies = mutableSetOf<String>()
    val enemies = mutableSetOf<String>()
    val factionPermission: FactionPermission = FactionPermission()

}