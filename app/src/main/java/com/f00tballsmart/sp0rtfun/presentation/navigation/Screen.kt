package com.f00tballsmart.sp0rtfun.presentation.navigation

import com.f00tballsmart.sp0rtfun.constants.*

sealed class Screen(val route: String){
    object Welcome: Screen(route = "welcome_screen"){
    }
    object Start: Screen(route = "start_screen")
    object Home: Screen(route = "home_screen")
    object Game: Screen(route = "game_screen/" +
            "{$PLAYER_TEAM_NAME}" +
            "/{$OPPONENT_TEAM_NAME}" +
            "/{$BET_ARGUMENT_KEY}" +
            "/{$ROUND_ARGUMENT_KEY}" +
            "/{$IS_PLAYER_AT_HOME}"){
        fun passBetAndRound(
        playerTeamName: String,
        opponentTeamName: String,
        bet: String,
        round: Int,
        isPlayerAtHome: Boolean
    ): String{//
            return "game_screen/$playerTeamName/$opponentTeamName/$bet/$round/$isPlayerAtHome"
        }
    }
//    object STATS: Screen(route = "stats_graph")
//    object TABLE: Screen(route = "table_graph")
//    object SETTINGS: Screen(route = "settings_graph")
//    object GAME: Screen(route = "game_graph")
}