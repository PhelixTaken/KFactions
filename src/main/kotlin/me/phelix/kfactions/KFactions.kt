package me.phelix.kfactions

import com.google.gson.reflect.TypeToken
import me.phelix.kfactions.commands.CommandHandler
import me.phelix.kfactions.events.JoinEvent
import me.phelix.kfactions.handlers.ChunkHandler
import me.phelix.kfactions.handlers.FactionHandler
import me.phelix.kfactions.handlers.PlayerHandler
import me.phelix.kfactions.utils.Config
import me.phelix.kfactions.utils.FLocation
import me.phelix.kfactions.utils.Message
import net.prosavage.baseplugin.serializer.Persist
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.lang.reflect.Type

class KFactions : JavaPlugin() {

    val playerHandler: PlayerHandler = PlayerHandler()
    val factionHandler: FactionHandler = FactionHandler()
    val chunkHandler: ChunkHandler = ChunkHandler(this)

    override fun onEnable() {
        getCommand("f")!!.setExecutor(CommandHandler(this))

        load()
        registerEvents()
    }

    override fun onDisable() {
        save()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(JoinEvent(this), this)
    }

    private fun load() {
        val factions = File("$dataFolder${File.separator}KFactions", "factions.json")
        val world = File("$dataFolder${File.separator}KFactions", "world.json")
        val message = File("$dataFolder${File.separator}KFactions", "messages.json")
        val config = File("$dataFolder${File.separator}KFactions", "config.json")
        val persist = Persist(dataFolder, logger)

        if (message.exists())
            Message.load(persist, message)

        if (config.exists())
            Config.load(persist, config)

        if (world.exists()) {
            val type: Type = object : TypeToken<HashMap<FLocation, String>>() {}.type
            chunkHandler.map = persist.load(type, world) ?: mutableMapOf()
        }

        factionHandler.map["Wilderness"] = Faction("Wilderness")
        factionHandler.map["Safezone"] = Faction("Safezone")
        factionHandler.map["Warzone"] = Faction("Warzone")


        if (factions.exists()) {
            val type: Type = object : TypeToken<HashMap<String, Faction>>() {}.type
            factionHandler.map = persist.load(type, factions) ?: mutableMapOf()

            factionHandler.map.forEach { (_, faction) ->

                faction.claims = mutableSetOf()
                faction.invites = mutableSetOf()

                faction.players.forEach {
                    playerHandler.map[it.id] = it
                    it.faction = faction
                }
            }

            chunkHandler.map.keys.forEach {
                val faction = factionHandler.getFaction(chunkHandler.map[it]!!)
                faction!!.claims.add(it)
            }
        }

    }

    private fun save() {
        val factions = File("$dataFolder${File.separator}KFactions", "factions.json")
        val world = File("$dataFolder${File.separator}KFactions", "world.json")
        val message = File("$dataFolder${File.separator}KFactions", "messages.json")
        val config = File("$dataFolder${File.separator}KFactions", "config.json")
        val persist = Persist(dataFolder, logger)
        factions.parentFile.mkdirs()
        factions.createNewFile()
        world.createNewFile()
        Message.save(persist, message)
        Config.save(persist, config)
        persist.save(false, factionHandler.map, factions)
        persist.save(false, chunkHandler.map, world)
    }


}