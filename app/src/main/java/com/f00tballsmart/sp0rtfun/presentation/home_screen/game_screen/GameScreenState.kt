package com.f00tballsmart.sp0rtfun.presentation.home_screen.game_screen

data class GameScreenState(
    val hasLost: Boolean = false,
    val timer: Int = 0,
    val timeInfo : String = "Win: 30",
)
