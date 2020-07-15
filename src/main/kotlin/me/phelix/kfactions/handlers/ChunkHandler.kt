package me.phelix.kfactions.handlers

import me.phelix.kfactions.Faction
import me.phelix.kfactions.KFactions
import me.phelix.kfactions.test
import me.phelix.kfactions.utils.FLocation
import org.bukkit.Chunk
import org.bukkit.Location


class ChunkHandler(val main: KFactions) {

    lateinit var map: MutableMap<FLocation, String>

    fun isClaimed(fLocation: FLocation): Boolean = map.containsKey(fLocation)
    fun isClaimed(location: Location): Boolean = isClaimed(FLocation(location))
    fun isClaimed(chunk: Chunk): Boolean = isClaimed(FLocation(chunk))

    fun claimChunk(chunk: Chunk, faction: Faction): Boolean {
        val location = FLocation(chunk)
        if(map.containsKey(location)) return false
        map[location] = faction.name
        faction.claims.add(location)
        return true
    }

    fun claimChunk(location: Location, faction: Faction): Boolean {
        val fLocation = FLocation(location)
        if(map.containsKey(fLocation)) return false
        map[fLocation] = faction.name
        faction.claims.add(fLocation)
        return true
    }

    fun claimChunk(location: FLocation, faction: Faction): Boolean {
        if(map.containsKey(location)) return false
        map[location] = faction.name
        faction.claims.add(location)
        return true
    }

    fun unclaimChunk(chunk: Chunk) {
        map.remove(FLocation(chunk))
    }

    fun unclaimChunk(location: Location) {
        map.remove(FLocation(location))
    }

    fun unclaimChunk(location: FLocation) {
        map.remove(location)
    }

    fun getFactionFromChunk(chunk: Chunk): Faction {
        return main.factionHandler.getFaction(map[FLocation(chunk)].toString()) ?: main.factionHandler.getWilderness()
    }

    fun getFactionFromChunk(location: Location): Faction {
        return main.factionHandler.getFaction(map[FLocation(location)].toString()) ?: main.factionHandler.getWilderness()
    }

    fun getFactionFromChunk(location: FLocation): Faction {
        return main.factionHandler.getFaction(map[location].toString()) ?: main.factionHandler.getWilderness()
    }

    fun getClaimedChunks(faction: Faction): ArrayList<FLocation> = ArrayList(faction.claims)


}