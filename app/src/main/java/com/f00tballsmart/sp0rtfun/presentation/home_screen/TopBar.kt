package com.f00tballsmart.sp0rtfun.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.UpdateStatusBarColor

@Composable
fun TopBar(){
    UpdateStatusBarColor(color = Color(0xFF127A5B))
    TopAppBar(
        backgroundColor = Color(0xFF127A5B),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
            )
        }
    }
}