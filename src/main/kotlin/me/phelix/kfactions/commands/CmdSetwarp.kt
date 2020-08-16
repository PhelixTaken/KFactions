package me.phelix.kfactions.commands

import me.phelix.kfactions.utils.Message
import me.phelix.kfactions.utils.Warp
import me.phelix.kfactions.utils.permissions.Permission

class CmdSetwarp : SubCommand(arrayOf("setwarp"), "<name> [password] [confirmPassword]", "Set a warp in the faction", true, Permission.SET_WARP) {

    override fun execute(commandContext: CommandContext) {
        commandContext.let {

            if (it.args.size in 1..3) {
                val name = it.args[0]

                if (name.equals("home", ignoreCase = true))
                    return@let it.fme.sendMessage(Message.setwarpHome)
                else {

                    var password: String? = null
                    var confirmPassword: String? = null

                    if (it.args.size == 2)
                        password = it.args[1]

                    if (it.args.size == 3)
                        confirmPassword = it.args[2]

                    val location = it.fme.getPlayer()!!.location

                    // If password and confirmPassword exists
                    if (password != null && confirmPassword != null) {
                        if (password != confirmPassword) { // If password and confirm password ain't the same
                            return@let it.fme.sendMessage(Message.setwarpPasswordNotSame)
                        } else { // If they are the same
                            val warp = Warp(name, password, location.world.uid, location.x, location.y, location.z)
                            it.myFaction.warps[name] = warp
                            it.fme.sendMessage(Message.setwarpWithPassword, name, password)
                        }
                    } else if (password != null && confirmPassword == null) { // If password exists but confirmPassword not
                        it.fme.sendMessage(Message.setwarpConfirm)
                    } else { // If both doesn't exist
                        val warp = Warp(name, null, location.world.uid, location.x, location.y, location.z)
                        it.myFaction.warps[name] = warp
                        it.fme.sendMessage(Message.setwarpWithoutPassword, name, location.x, location.y, location.z)
                    }
                }
            } else {
                it.fme.sendMessage(toString())
            }
        }
    }

}