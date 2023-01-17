package com.f00tballsmart.sp0rtfun.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TabBarScreen

@Composable
fun TabBar(
    navHostController: NavHostController
){
    val screens = listOf(
        TabBarScreen.Main,
        TabBarScreen.Stats,
        TabBarScreen.Table,
        TabBarScreen.Settings,
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Color(0xFF303030)
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: TabBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.label)
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.high, red = 1f, blue = 1f, green = 1f ),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled, red = 1f, blue = 1f, green = 1f),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}