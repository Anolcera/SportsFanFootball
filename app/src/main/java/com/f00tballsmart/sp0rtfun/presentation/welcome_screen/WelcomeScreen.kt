package com.f00tballsmart.sp0rtfun.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TabBarScreen
import com.f00tballsmart.sp0rtfun.presentation.navigation.Screen
import com.f00tballsmart.sp0rtfun.presentation.welcome_screen.WelcomeScreenViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: WelcomeScreenViewModel = hiltViewModel()
){
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.isSystemBarsVisible = false
        systemUiController.isStatusBarVisible = false
    }

    Image(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.welcome_img),
        contentDescription = stringResource(R.string.welcome_img_descriptor),
        contentScale = ContentScale.FillHeight,
    )

    val state = viewModel.state
    val coroutineScope = rememberCoroutineScope()


    coroutineScope.launch {
        delay(2000L)
        if (state.value.shouldShowStartScreen) {
            navController.popBackStack()
            navController.navigate(route = Screen.Start.route)
        } else {
            navController.popBackStack()
            //navController.navigate(route = Screen.Home.route)
            navController.navigate(TabBarScreen.Main.route)
        }
    }
}