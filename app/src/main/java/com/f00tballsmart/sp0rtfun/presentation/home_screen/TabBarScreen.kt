package com.f00tballsmart.sp0rtfun.presentation.home_screen

import com.f00tballsmart.sp0rtfun.R

sealed class TabBarScreen(
    val route: String,
    val label: String,
    val icon: Int
) {
    object Main : TabBarScreen(
        route = "MAIN",
        label = "Main",
        icon = R.drawable.ic_tabbar_main_icon,
    )

    object Stats : TabBarScreen(
        route = "STATS",
        label = "Stats",
        icon = R.drawable.ic_tabbar_stats_icon
    )

    object Table : TabBarScreen(
        route = "TABLE",
        label = "Table",
        icon = R.drawable.ic_tabbar_table_icon
    )

    object Settings : TabBarScreen(
        route = "SETTINGS",
        label = "Settings",
        icon = R.drawable.ic_tabbar_settings_icon
    )
}