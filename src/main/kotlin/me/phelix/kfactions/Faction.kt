package me.phelix.kfactions

import me.phelix.kfactions.utils.FLocation

class Faction(val name: String) {

    @Transient val claims = mutableSetOf<FLocation>()
    val players = mutableSetOf<FPlayer>()
    val allies = mutableSetOf<String>()



}

fun Any?.test() {

}