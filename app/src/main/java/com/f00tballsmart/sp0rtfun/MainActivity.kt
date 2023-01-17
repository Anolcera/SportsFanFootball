package com.f00tballsmart.sp0rtfun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.presentation.navigation.HomeNavGraph
import com.f00tballsmart.sp0rtfun.presentation.navigation.RootNavigationGraph
import com.f00tballsmart.sp0rtfun.presentation.navigation.TabBar
import com.f00tballsmart.sp0rtfun.ui.theme.SportsFanFootballTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsFanFootballTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { TabBar(navHostController = navController) }
                ) { innerPadding  ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ){
                        RootNavigationGraph(navController = navController)
                    }
                }
                //RootNavigationGraph(navController = rememberNavController())

                //test()
            }
        }
    }
}