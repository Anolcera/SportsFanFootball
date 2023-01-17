package com.f00tballsmart.sp0rtfun.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.presentation.navigation.HomeNavGraph
import com.f00tballsmart.sp0rtfun.presentation.navigation.TabBar

@Composable
fun HomeScreen(navController : NavHostController = rememberNavController()){
    Scaffold(
        bottomBar = { TabBar(navHostController = navController) }
    ) { innerPadding  ->

        Box(
            modifier = Modifier.padding(innerPadding)
        ){
            HomeNavGraph(navController = navController)
        }
    }
}