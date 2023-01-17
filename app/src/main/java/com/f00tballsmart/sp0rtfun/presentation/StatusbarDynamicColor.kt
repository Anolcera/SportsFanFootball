package com.f00tballsmart.sp0rtfun.constants

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


var statusBarColor: StateFlow<Color> = MutableStateFlow(Color(0x00000000))


@Composable
fun UpdateStatusBarColor(
    color: Color
){
    val systemUiController = rememberSystemUiController()
    SideEffect {
        //systemUiController.isStatusBarVisible = false
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false,
        )

    }
}
