package com.f00tballsmart.sp0rtfun.presentation.home_screen.main_screen

import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.domain.model.Game

data class MainScreenState(
    val games: List<Game> = emptyList(),
    val playerTeamGames: List<Game> = emptyList(),
    val playerTeam: PlayerTeam = PlayerTeam(),
    val isLoading: Boolean = false,
)
