package com.f00tballsmart.sp0rtfun.presentation.home_screen.game_screen

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.DRAW_TIME
import com.f00tballsmart.sp0rtfun.constants.WIN_TIME
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TabBarScreen
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TopBar
import com.f00tballsmart.sp0rtfun.util.GameResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun GameScreen(
    viewModel : GameScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val state = viewModel.state
    var ticks by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler {}

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            viewModel.updateTimer(++ticks)

            if(ticks > DRAW_TIME){
                viewModel.updateCurrentGameStats(GameResult.Lose, DRAW_TIME)
                navigateToHomeScreen(navController)
            }else if(ticks > WIN_TIME){
                viewModel.updateTimeInfo()
            }
        }
    }


    val onItemClick: (Int) -> Unit = {currentIndex ->
        coroutineScope.launch {
            if(viewModel.chosenTeamIndex != currentIndex && viewModel.currentTeam.isEmpty()){
                viewModel.openCurrentIndexCard(currentIndex)


                if (viewModel.chosenTeam.isEmpty()){
                    viewModel.chosenTeam = viewModel.teamList[currentIndex]
                    viewModel.chosenTeamIndex = currentIndex

                }else{
                    viewModel.currentTeam = viewModel.teamList[currentIndex]
                    if(viewModel.chosenTeam == viewModel.teamList[currentIndex]){
                        viewModel.openedCards += 1
                        if(viewModel.openedCards == 9){
                            val time = state.timer
                            if (time <= WIN_TIME){
                                viewModel.updateCurrentGameStats(GameResult.Win, time)
                            }else if(time <= DRAW_TIME){
                                viewModel.updateCurrentGameStats(GameResult.Draw, time)
                            }
                            navigateToHomeScreen(navController)
                        }
                    }else{
                        delay(500L)
                        viewModel.closeIndexedCards(currentIndex)
                    }
                    viewModel.chosenTeam = ""
                    viewModel.currentTeam = ""
                    viewModel.chosenTeamIndex = -1
                }


            }
        }
    }

    Scaffold(
        topBar = {TopBar()},
        backgroundColor = Color(0xFF585858)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                //.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .weight(2f)
                ){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(110.dp)
                            .height(28.dp)
                            .background(Color.Black)
                    ){
                        Text(
                            text = state.timeInfo,
                            color = Color.Yellow,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500),
                        )
                    }
                }


                Text(
                    text = "Time ${state.timer}",
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 26.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 28.dp, end = 28.dp)

            ) {

                CardRow(0, viewModel.imagesState, onItemClick)
                CardRow(1, viewModel.imagesState, onItemClick)
                CardRow(2, viewModel.imagesState, onItemClick)
                CardRow(3, viewModel.imagesState, onItemClick)
                CardRow(4, viewModel.imagesState, onItemClick)
            }
        }
    }
}

@Composable
private fun CardRow(
    rowIndex: Int,
    images: List<Int>,
    onItemClick: (Int) -> Unit
){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ){
        var indices : List<Int> = emptyList()
        @Suppress("UNUSED_EXPRESSION")
        when(rowIndex){
            0 -> indices = listOf(0,1,2,3)
            1 -> indices = listOf(4,5,6,7)
            2 -> indices = listOf(7,8,9,10)
            3 -> indices = listOf(11,12,13,14)
            4 -> indices = listOf(14,15,16, 17)
            else -> null
        }
        CardButton(
            cardButtonIndex = indices[0],
            images[indices[0]],
            onItemClick,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        CardButton(
            cardButtonIndex = indices[1],
            images[indices[1]],
            onItemClick,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp)
        )
        CardButton(
            cardButtonIndex = indices[2],
            images[indices[2]],
            onItemClick,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp)
        )
        if(rowIndex % 2 == 0) {
            CardButton(
                cardButtonIndex = indices[3],
                images[indices[3]],
                onItemClick,
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp)
            )
        }
    }
}

@Composable
private fun CardButton(
    cardButtonIndex: Int,
    @DrawableRes imageSource: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier
){

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable {
                onItemClick(cardButtonIndex)
            }

    ) {
        CardImage(imageSource)
    }
}


@Composable
private fun CardImage(
    @DrawableRes painterResourceId: Int,
){
    Image(
        painter = painterResource(id = painterResourceId),
        contentDescription = stringResource(R.string.card_image),
    )
}

private fun navigateToHomeScreen(navController: NavController){
    //navController.popBackStack()
    //navController.navigate(TabBarScreen.Main.route)
    navController.navigate(TabBarScreen.Main.route){
        popUpTo(TabBarScreen.Main.route){
            inclusive = true
        }
    }
}






