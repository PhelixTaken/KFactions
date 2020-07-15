package me.phelix.kfactions.utils

import net.prosavage.baseplugin.serializer.Persist
import java.io.File

object Config {

    @Transient private val instance = this

    var factionNameMinLength = 4
    var factionNameMaxLength = 16

    var factionPowerPerPlayer = 10

    fun save(persist: Persist, file: File) = persist.save(false, instance, file)

    fun load(persist: Persist, file: File) = persist.load(Config::class.java, file)

}