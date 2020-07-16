package me.phelix.kfactions.handlers

import me.phelix.kfactions.Faction

class FactionHandler {

    var map: MutableMap<String, Faction> = mutableMapOf()

    fun getFaction(name: String): Faction? = map[name]

    fun getWilderness(): Faction = map["Wilderness"]!!

}