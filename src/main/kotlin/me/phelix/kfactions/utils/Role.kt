package me.phelix.kfactions.utils

enum class Role(val value: Int, val prefix: String) {

    LEADER(5, "Leader"),
    COLEADER(4, "Co-Leader"),
    MODERATOR(3, "Moderator"),
    MEMBER(2, "Member"),
    RECRUIT(1, "Recruit"),
    NONE(0, "None");

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