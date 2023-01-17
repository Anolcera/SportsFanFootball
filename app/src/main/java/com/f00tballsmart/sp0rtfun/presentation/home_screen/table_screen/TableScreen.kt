package com.f00tballsmart.sp0rtfun.presentation.home_screen.table_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TopBar


@Composable
fun TableScreen(
    viewModel: TableScreenViewModel = hiltViewModel()
){
    val state = viewModel.state
    Scaffold(
        topBar = { TopBar()},
        backgroundColor = Color(0xFF404040)
    ) {
        TableHead()
        LazyColumn(
            //contentPadding = PaddingValues(bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(10.7.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 33.5.dp,
                )
        ){
            items(state.teams.size){index ->
                val team = state.teams[index]
                TableItem(teamName = team.name, win = team.win, lose = team.lose, draw = team.draw, point = team.point)
            }
        }
    }
}

@Composable
fun TableHead(
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(28.dp)
            .background(Color(0xFF585858))
    ){
        Text(
            text = "Team",
            color = Color.White,
            modifier = Modifier
                .padding(start = 24.dp)
                .weight(7f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(3f)
                .padding(end = 24.dp)
        ) {
            Text(
                text = "W",
                color = Color.Yellow,

            )
            Text(
                text = "L",
                color = Color.Yellow,

            )
            Text(
                text = "D",
                color = Color.Yellow,
            )
            Text(
                text = "P",
                color = Color.Yellow,
            )
        }
    }
}

@Composable
fun TableItem(
    modifier: Modifier = Modifier,
    teamName: String,
    win: Int,
    lose: Int,
    draw: Int,
    point: Int,
){
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ){
            Text(
                text = teamName,
                color = Color(0xB3FFFFFF),
                modifier = Modifier
                    .padding(start = 24.dp)
                    .weight(7f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 24.dp)
            ) {
                Text(
                    text = win.toString(),
                    color = Color(0xB3FFFFFF),
                )
                Text(
                    text = lose.toString(),
                    color = Color(0xB3FFFFFF),
                )
                Text(
                    text = draw.toString(),
                    color = Color(0xB3FFFFFF),
                )
                Text(
                    text = point.toString(),
                    color = Color(0xB3FFFFFF),
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, start = 16.dp, end = 16.dp)
                .background(Color(0xFF585858))
        )
    }
}