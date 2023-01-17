package com.f00tballsmart.sp0rtfun.presentation.home_screen.stats_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.TeamLogosRoundBig
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TopBar

@Preview(showBackground = true)
@Composable
fun StatsScreen(
    viewModel: StatsScreenViewModel = hiltViewModel()
){
    val playerTeam = viewModel.state.playerTeam
    Scaffold(
        topBar = { TopBar()},
        backgroundColor = Color(0xFF404040)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TeamLogoAndName(
                teamName = playerTeam.teamName,
                logo = TeamLogosRoundBig().logos[playerTeam.teamName]!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
            StatItem(
                icon = R.drawable.ic_win_icon,
                statName = "Won",
                statCount = playerTeam.win.toString(),
            )
            StatItem(
                icon = R.drawable.ic_lost_icon,
                statName = "Lost",
                statCount = playerTeam.lose.toString(),
            )
            StatItem(
                icon = R.drawable.ic_draw_icon,
                statName = "Draws",
                statCount = playerTeam.draw.toString(),
            )
            StatItem(
                icon = R.drawable.ic_average_time_icon,
                statName = "Average Time",
                statCount = playerTeam.averageTime.toString(),
                showDivider = false
            )

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color(0xFF585858))
            ){
                Text(
                    text = "Odds",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 40.dp)
                )
            }

            StatItem(
                icon = R.drawable.ic_win_icon,
                statName = "Won",
                statCount = playerTeam.winOdds.toString(),
            )
            StatItem(
                icon = R.drawable.ic_lost_icon,
                statName = "Lost",
                statCount = playerTeam.loseOdds.toString(),
                showDivider = false
            )
        }
    }

}

@Composable
private fun TeamLogoAndName(
    modifier : Modifier = Modifier,
    teamName: String,
    @DrawableRes logo: Int,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(Color(0xFF585858))
    ){
        Text(
            text = teamName,
            Modifier.padding(
                start = 24.dp
            ),
            color = Color.White
        )
        Image(
            painter = painterResource(id = logo),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 24.dp)
                .size(64.dp)
        )
    }
}

@Composable
private fun StatItem(
    @DrawableRes icon : Int,
    statName: String,
    statCount: String,
    showDivider: Boolean = true
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(20.dp)
                    .background(Color(0xFF127A5B))

            ){
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "win icon",
                    modifier = Modifier
                        .size(12.dp)
                )
            }
            Text(
                text = statName,
                color = Color(0xB3FFFFFF),
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Text(
            text = statCount,
            color = Color.Yellow,
            modifier = Modifier
                .padding(end = 16.dp)
        )
    }

    if(showDivider){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
                .background(Color(0xFF585858))
        )
    }
}