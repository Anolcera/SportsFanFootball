package com.f00tballsmart.sp0rtfun.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.f00tballsmart.sp0rtfun.constants.*
import com.f00tballsmart.sp0rtfun.presentation.WelcomeScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TabBarScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.game_screen.GameScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.main_screen.MainScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.settings_screen.SettingsScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.stats_screen.StatsScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.table_screen.TableScreen
import com.f00tballsmart.sp0rtfun.presentation.start_screen.StartScreen

@Composable
fun RootNavigationGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        route = "root_graph",
        startDestination = Screen.Welcome.route
    ){

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Start.route){
            StartScreen(navController = navController)
        }

        composable(
            route = Screen.Home.route,
        ){
            //HomeScreen()
        }

        composable(
            route = TabBarScreen.Main.route,
        ){
            MainScreen(navController = navController)
        }

        composable(route = TabBarScreen.Stats.route){
            StatsScreen()//navController = navController)
        }

        composable(route = TabBarScreen.Table.route){
            TableScreen()
        }

        composable(route = TabBarScreen.Settings.route){
            SettingsScreen(navController = navController)
        }

        composable(
            route = Screen.Game.route,
            arguments = listOf(
                navArgument(PLAYER_TEAM_NAME){
                    type = NavType.StringType
                },
                navArgument(OPPONENT_TEAM_NAME){
                    type = NavType.StringType
                },
                navArgument(BET_ARGUMENT_KEY){
                    type = NavType.StringType
                },
                navArgument(ROUND_ARGUMENT_KEY){
                    type = NavType.IntType
                },
                navArgument(IS_PLAYER_AT_HOME){
                    type = NavType.BoolType
                }
            )
        ){
            GameScreen(navController = navController)
        }

    }
}