package me.phelix.kfactions.utils.permissions

import me.phelix.kfactions.utils.Role
import me.phelix.kfactions.utils.Role.*
import me.phelix.kfactions.utils.permissions.Permission.*

class FactionPermission {

    val map = mutableMapOf<Role, HashSet<Permission>>()

    fun hasPermission(role: Role, permission: Permission): Boolean = map[role]!!.contains(permission)

    fun addPermission(role: Role, permission: Permission) = map[role]!!.add(permission)

    fun removePermission(role: Role, permission: Permission) = map[role]!!.remove(permission)

    fun setDefaultPermissions() {

        val recruit = RECRUIT
        val member = MEMBER
        val moderator = MODERATOR
        val coleader = COLEADER
        val leader = LEADER

        map[recruit] = HashSet()
        map[member] = HashSet()
        map[moderator] = HashSet()
        map[coleader] = HashSet()
        map[leader] = HashSet()

        Permission.values().forEach {

            when (it) {
                Permission.NONE, CHAT -> {
                    map[recruit]!!.add(it)
                    map[member]!!.add(it)
                    map[moderator]!!.add(it)
                    map[coleader]!!.add(it)
                    map[leader]!!.add(it)
                }
                PLACING, BREAKING, CLAIMING, UNCLAIMING, WARP, HOME, WARP_LIST -> {
                    map[member]!!.add(it)
                    map[moderator]!!.add(it)
                    map[coleader]!!.add(it)
                    map[leader]!!.add(it)
                }
                KICK, INVITING -> {
                    map[moderator]!!.add(it)
                    map[coleader]!!.add(it)
                    map[leader]!!.add(it)
                }
                UNCLAIMING_ALL, DEMOTE, PROMOTE, SET_WARP, DELETE_WARP, SET_DESCRIPTION,
                ADD_ALLY, REMOVE_ALLY, SET_OPEN, SET_HOME, BAN, UNBAN, ENEMY, REMOVE_ENEMY -> {
                    map[coleader]!!.add(it)
                    map[leader]!!.add(it)
                }
                DISBAND, DEFAULT_ROLE, PERMS -> map[leader]!!.add(it)

            }
        }
    }

}