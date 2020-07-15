package me.phelix.kfactions.utils

import net.prosavage.baseplugin.serializer.Persist
import java.io.File

object Message {

    @Transient
    private val instance = this

    var nice = "test"

    fun save(persist: Persist, file: File) = persist.save(false, instance, file)

    fun load(persist: Persist, file: File) = persist.load(Message::class.java, file)

}