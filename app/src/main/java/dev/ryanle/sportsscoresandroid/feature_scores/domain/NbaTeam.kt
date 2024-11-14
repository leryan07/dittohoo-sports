package dev.ryanle.sportsscoresandroid.feature_scores.domain

import dev.ryanle.sportsscoresandroid.R

enum class NbaTeam(
    override val drawableResId: Int?,
    override val teamName: String
) : Team {
    CELTICS(R.drawable.boston_celtics_logo, "Celtics"),
    NETS(R.drawable.brooklyn_nets_logo, "Nets"),
    KNICKS(R.drawable.new_york_knicks_logo, "Knicks"),
    SIXERS(R.drawable.philadelphia_sixers_logo, "76ers"),
    RAPTORS(R.drawable.toronto_raptors_logo, "Raptors"),
    BULLS(R.drawable.chicago_bulls_logo, "Bulls"),
    CAVALIERS(R.drawable.cleveland_cavaliers_logo, "Cavaliers"),
    PISTONS(R.drawable.detroit_pistons_logo, "Pistons"),
    PACERS(R.drawable.indiana_pacers_logo, "Pacers"),
    BUCKS(R.drawable.milwaukee_bucks_logo, "Bucks"),
    NUGGETS(R.drawable.denver_nuggets_logo, "Nuggets"),
    TIMBERWOLVES(R.drawable.minnesota_timberwolves_logo, "Timberwolves"),
    THUNDER(R.drawable.oklahoma_city_thunder_logo, "Thunder"),
    TRAIL_BLAZERS(R.drawable.portland_trail_blazers_logo, "Trail Blazers"),
    WARRIORS(R.drawable.golden_state_warriors_logo, "Warriors"),
    CLIPPERS(R.drawable.la_clippers_logo, "Clippers"),
    LAKERS(R.drawable.la_lakers_logo, "Lakers"),
    SUNS(R.drawable.phoenix_suns_logo, "Suns"),
    KINGS(R.drawable.sacramento_kings_logo, "Kings"),
    HAWKS(R.drawable.atlanta_hawks_logo, "Hawks"),
    HORNETS(R.drawable.charlotte_hornets_logo, "Hornets"),
    HEAT(R.drawable.miami_heat_logo, "Heat"),
    MAGIC(R.drawable.orlando_magic_logo, "Magic"),
    WIZARDS(R.drawable.washington_wizards_logo, "Wizards"),
    MAVERICKS(R.drawable.dallas_mavericks_logo, "Mavericks"),
    ROCKETS(R.drawable.houston_rockets_logo, "Rockets"),
    GRIZZLIES(R.drawable.memphis_grizzlies_logo, "Grizzlies"),
    PELICANS(R.drawable.new_orleans_pelicans_logo, "Pelicans"),
    SPURS(R.drawable.san_antonio_spurs_logo, "Spurs"),
    JAZZ(R.drawable.utah_jazz_logo, "Jazz"),
    UNKNOWN(null, "Unknown");

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