package me.phelix.kfactions.utils

import net.prosavage.baseplugin.serializer.Persist
import java.io.File

object Message {

    @Transient private val instance = this

    var prefix = "&7[&6!&7]"
    var commandMapPrefix = "&m&l&8=======================< &r&6Map &r&m&l&8>======================="

    var playerNoPermission = "&7You are lacking the permission(s) &6%s&7!"

    var factionNeeded = "&7You must be in a faction to do that!"

    var alreadyInFaction = "&7You are already in a faction!"

    var factionNameLength = "&7The faction's name must have minimal &6%s &7characters and can have a maximum of &6%s &7characters!"
    var factionCreated = "&7You have successfully created the faction &6%s&7!"

    var playerNotExist = "&7The player &6%s &7does not exist!"
    var factionNotExist = "&7The faction &6%s &7does not exist!"

    var descriptionChanged = "&7Changed faction's description to &6%s&7!"

    var invalidFactionName = "&7Faction name can't be &6%s&7, &6%s &7or &6%s&7!"

    var playerAlreadyInSameFaction = "&6%s &7is already in the same faction as you!"
    var playerIsAlreadyInFaction = "&6%s &7is already in a faction!"

    var playerInviteBan = "&7You can't invite someone that is banned from the faction!"
    var playerInvited = "&7You have invited &6%s &7to the faction!"
    var playerNotificationInvited = "&7You have been invited by &6%s &7to &6%s&7! &o(( Click here ))"

    var roleDoesNotExist = "&7The role &6%s &7does not exist!"
    var defaultRoleSet = "&7You have set the default faction role to &6%s&7!"

    var factionClosedNotInvited = "&7The faction &6%s &7is closed and you are not invited!"
    var factionJoinBan = "&7You can't join &6%s &7since you are banned!"
    var factionJoined = "&7You have joined &6%s&7!"
    var factionJoinBroadcast = "&6%s &7has joined the faction!"

    var factionNotSame = "&7You are not in the same faction as &6%s&7!"

    var playerKicked = "&7You have kicked &6%s &7from the faction!"
    var targetKicked = "&7You have been kicked from the faction by &6%s&7!"
    var broadcastKick = "&6%s &7has been kicked from the faction by &6%s&7!"

    var playerBan = "&7You have banned &6%s &7from the faction!"
    var targetBan = "&7You have been banned from the faction by &6%s&7!"
    var broadcastBan = "&6%s &7has been banned from the faction by &6%s&7!"

    var disbandConfirmMessage = "&7Please confirm disbanding by doing &6/f disband confirm&7! ( Cannot be undone! )"
    var disbandAlreadyConfirm = "&7Please confirm disbanding by doing &6/f disband confirm&7!"
    var disbandNotConfirmed = "&7Please run &6/disband &7first!"
    var disbandSuccessfully = "&7You have disbanded &6%s&7!"
    var disbandBroadcast = "&6%s &7has been disbanded by &6%s&7!"

    var chunkClaimedBySelfFaction = "&7The chunk &6%s&7;&6%s &7has been already claimed by your faction!"
    var chunkClaimedByEnemy = "&7The chunk &6%s&7;&6%s &7has been already claimed by &6%s&7!"
    var chunkClaimPowerShortness = "&7You don't have enough power to claim this chunk!"
    var chunkClaimed = "&7You have claimed the chunk &6%s&7;&6%s&7!"
    var chunkClaimBroadcast = "&7The chunk &6%s&7;&6%s&7 has been claimed by &6%s&7!"

    var unclaimChunkNotClaimed = "&7The chunk &6%s&7;&6%s&7 hasn't been claimed by anyone!"
    var unclaimChunkEnemy = "&7You can't unclaim enemy's faction!"
    var unclaimChunk = "&7You have unclaimed the chunk &6%s&7;&6%s&7!"
    var unclaimChunkBroadcast = "&7The chunk &6%s&7;&6%s&7 has been unclaimed by &6%s&7!"

    var unclaimAllNone = "&7Your faction has no claims to unclaim!"
    var unclaimAll = "&7You have unclaimed all the chunks in the faction!"
    var unclaimAllBroadcast = "&6%s &7has unclaimed all the chunks in the faction!"

    var setwarpHome = "&7To set home warp use &6/f sethome&7!"
    var setwarpWithoutPassword = "&7You have set the warp &6%s&7 at &6%s&7;&6%s&7!"
    var setwarpPasswordNotSame = "&7The password isn't the same as the confirmed password!"
    var setwarpWithPassword = "&7You have set the warp &6%s&7 with the password &6%s&7!"
    var setwarpConfirm = "&7Please confirm the password behind the first password!"

    var deleteWarpNotExist = "&7The warp &6%s &7does not exist!"
    var deleteWarpPasswordNeeded = "&7You need to confirm with the password of the warp &6%s&7!"
    var deleteWarpPasswordNotMatch = "&7The password of the warp &6%s &7doesn't match!"
    var deleteWarp = "&7Deleted the warp &6%s&7!"

    var show = arrayListOf(
        "&8&l&m===========================================",
        "&7Name \u00BB &6{faction_name}",
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