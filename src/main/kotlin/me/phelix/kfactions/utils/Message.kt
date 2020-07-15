package me.phelix.kfactions.utils

import net.prosavage.baseplugin.serializer.Persist
import java.io.File

object Message {

    @Transient private val instance = this

    var prefix = "&7[&6!&7]"
    var commandMapPrefix = "&m&l&8=======================< &r&6Map &r&m&l&8>======================="

    var factionNeeded = "&7You must be in a faction to do that!"

    var alreadyInFaction = "&7You are already in a faction!"

    var factionNameLength = "&7The faction's name must have minimal &6%s &7characters and can have a maximum of &6%s&7!"
    var factionCreated = "&7You have successfully created the faction &6%s&7!"



    var show = arrayListOf(
        "&8&l&m===========================================",
        "&7Description \u00BB &6{faction_description}",
        "&7Leader \u00BB &6{faction_leader}",
        "&7Members \u00BB &6{faction_members}",
        "&7Claims \u00BB &6{faction_claims}&7/&6{faction_totalclaims}",
        "&7Power \u00BB &6{faction_powerleft}&7/&6{faction_totalpower}",
        "&7Allies \u00BB &6{faction_allies}",
        "&7Enemies \u00BB &6{faction_enemies}",
        "&8&l&m==========================================="
    )

    fun save(persist: Persist, file: File) = persist.save(false, instance, file)

    fun load(persist: Persist, file: File) = persist.load(Message::class.java, file)

}