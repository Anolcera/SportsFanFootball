package com.f00tballsmart.sp0rtfun.presentation.home_screen.settings_screen

data class SettingsState(
    val soundChecked: Boolean = false,
    val musicChecked: Boolean = false,
    val vibrationChecked : Boolean = false,
    val showDialog: Boolean = false,
)
