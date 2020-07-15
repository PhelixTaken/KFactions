package me.phelix.kfactions.utils

enum class Role(val value: Int) {

    LEADER(5),
    COLEADER(4),
    MODERATOR(3),
    MEMBER(2),
    RECRUIT(1),
    NONE(0);

    fun getByValue(value: Int): Role {
        when(value) {
            0 -> NONE
            1 -> RECRUIT
            2 -> MEMBER
            3 -> MODERATOR
            4 -> COLEADER
            5 -> LEADER
        }
        return NONE
    }

    fun isAtLeast(role: Role): Boolean = value >= role.value

    fun isAtmost(role: Role): Boolean = value <= role.value


}