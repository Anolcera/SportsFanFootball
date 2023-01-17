package com.f00tballsmart.sp0rtfun.presentation.home_screen.main_screen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.presentation.home_screen.MatchViewItem
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TopBar
import com.f00tballsmart.sp0rtfun.presentation.navigation.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun MainScreen(
    navController: NavController = rememberNavController(),
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val state = viewModel.state

    val systemUiController = rememberSystemUiController()
    SideEffect {
        if(!systemUiController.isSystemBarsVisible && !systemUiController.isStatusBarVisible){
            systemUiController.isSystemBarsVisible = true
            systemUiController.isStatusBarVisible = true
        }
    }


    Scaffold(
        topBar = { TopBar()},
        backgroundColor = Color(0xFF404040)
    ) {
        LazyColumn(
        ){
            items(state.playerTeamGames.size){ i->
                val game = state.playerTeamGames[i]
                MatchViewItem(
                    game = game,
                    onOddsClick = { bet->

                        if(i >= state.playerTeam.playedRound){
                            val isPlayerAtHome: Boolean
                            val opponentTeam = if(game.homeTeam.name == state.playerTeam.teamName){
                                isPlayerAtHome = true
                                game.awayTeam.name
                            }else{
                                isPlayerAtHome = false
                                game.homeTeam.name
                            }

                            navController.navigate(
                                Screen.Game
                                    .passBetAndRound(
                                        playerTeamName = state.playerTeam.teamName,
                                        opponentTeamName = opponentTeam,
                                        bet = bet.bet,
                                        round = game.roundNumber,
                                        isPlayerAtHome = isPlayerAtHome
                                    )
                            ){
                                popUpTo(
                                    Screen.Game
                                        .passBetAndRound(
                                            playerTeamName = state.playerTeam.teamName,
                                            opponentTeamName = opponentTeam,
                                            bet = bet.bet,
                                            round = game.roundNumber,
                                            isPlayerAtHome = isPlayerAtHome
                                        )
                                ){
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
