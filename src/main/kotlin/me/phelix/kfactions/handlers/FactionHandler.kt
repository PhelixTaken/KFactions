package me.phelix.kfactions.handlers

import me.phelix.kfactions.Faction

class FactionHandler {

    lateinit var map: MutableMap<String, Faction>

    fun getFaction(name: String): Faction? = map[name]

    fun getWilderness(): Faction = map["Wilderness"]!!

}