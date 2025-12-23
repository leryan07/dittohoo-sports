package com.areazeroseven.dittohoosports.matchups.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.areazeroseven.dittohoosports.R

enum class NbaTeam(
    @DrawableRes override val drawableResId: Int,
    @StringRes override val teamNameResId: Int
) : Team {
    CELTICS(R.drawable.boston_celtics_logo, R.string.celtics),
    NETS(R.drawable.brooklyn_nets_logo, R.string.nets),
    KNICKS(R.drawable.new_york_knicks_logo, R.string.knicks),
    SIXERS(R.drawable.philadelphia_sixers_logo, R.string._76ers),
    RAPTORS(R.drawable.toronto_raptors_logo, R.string.raptors),
    BULLS(R.drawable.chicago_bulls_logo, R.string.bulls),
    CAVALIERS(R.drawable.cleveland_cavaliers_logo, R.string.cavaliers),
    PISTONS(R.drawable.detroit_pistons_logo, R.string.pistons),
    PACERS(R.drawable.indiana_pacers_logo, R.string.pacers),
    BUCKS(R.drawable.milwaukee_bucks_logo, R.string.bucks),
    NUGGETS(R.drawable.denver_nuggets_logo, R.string.nuggets),
    TIMBERWOLVES(R.drawable.minnesota_timberwolves_logo, R.string.timberwolves),
    THUNDER(R.drawable.oklahoma_city_thunder_logo, R.string.thunder),
    TRAIL_BLAZERS(R.drawable.portland_trail_blazers_logo, R.string.trail_blazers),
    WARRIORS(R.drawable.golden_state_warriors_logo, R.string.warriors),
    CLIPPERS(R.drawable.la_clippers_logo, R.string.clippers),
    LAKERS(R.drawable.la_lakers_logo, R.string.lakers),
    SUNS(R.drawable.phoenix_suns_logo, R.string.suns),
    KINGS(R.drawable.sacramento_kings_logo, R.string.kings),
    HAWKS(R.drawable.atlanta_hawks_logo, R.string.hawks),
    HORNETS(R.drawable.charlotte_hornets_logo, R.string.hornets),
    HEAT(R.drawable.miami_heat_logo, R.string.heat),
    MAGIC(R.drawable.orlando_magic_logo, R.string.magic),
    WIZARDS(R.drawable.washington_wizards_logo, R.string.wizards),
    MAVERICKS(R.drawable.dallas_mavericks_logo, R.string.mavericks),
    ROCKETS(R.drawable.houston_rockets_logo, R.string.rockets),
    GRIZZLIES(R.drawable.memphis_grizzlies_logo, R.string.grizzlies),
    PELICANS(R.drawable.new_orleans_pelicans_logo, R.string.pelicans),
    SPURS(R.drawable.san_antonio_spurs_logo, R.string.spurs),
    JAZZ(R.drawable.utah_jazz_logo, R.string.jazz),
    UNKNOWN(R.drawable.broken_image_24dp, R.string.unknown);

    companion object {
        fun fromValue(value: String): NbaTeam {
            return when (value.lowercase()) {
                "celtics" -> CELTICS
                "nets" -> NETS
                "knicks" -> KNICKS
                "76ers" -> SIXERS
                "raptors" -> RAPTORS
                "bulls" -> BULLS
                "cavaliers" -> CAVALIERS
                "pistons" -> PISTONS
                "pacers" -> PACERS
                "bucks" -> BUCKS
                "nuggets" -> NUGGETS
                "timberwolves" -> TIMBERWOLVES
                "thunder" -> THUNDER
                "trail blazers" -> TRAIL_BLAZERS
                "warriors" -> WARRIORS
                "clippers" -> CLIPPERS
                "lakers" -> LAKERS
                "suns" -> SUNS
                "kings" -> KINGS
                "hawks" -> HAWKS
                "hornets" -> HORNETS
                "heat" -> HEAT
                "magic" -> MAGIC
                "wizards" -> WIZARDS
                "mavericks" -> MAVERICKS
                "rockets" -> ROCKETS
                "grizzlies" -> GRIZZLIES
                "pelicans" -> PELICANS
                "spurs" -> SPURS
                "jazz" -> JAZZ
                else -> UNKNOWN
            }
        }
    }
}