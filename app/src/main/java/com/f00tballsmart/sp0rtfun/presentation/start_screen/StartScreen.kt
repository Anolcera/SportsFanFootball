package com.f00tballsmart.sp0rtfun.presentation.start_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.UpdateStatusBarColor
import com.f00tballsmart.sp0rtfun.domain.use_case.put_teams_in_db.teamMaker
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TabBarScreen
import com.f00tballsmart.sp0rtfun.presentation.navigation.Screen
import com.f00tballsmart.sp0rtfun.ui.theme.Futura
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartScreenViewModel = hiltViewModel()
){
    val state = viewModel.state
    val coroutineScope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        if(!systemUiController.isSystemBarsVisible && !systemUiController.isStatusBarVisible){
            systemUiController.isSystemBarsVisible = true
            systemUiController.isStatusBarVisible = true
        }
    }

    UpdateStatusBarColor(state.statusBarColor)

    Scaffold(
        backgroundColor = Color(0xFF585858),
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF303030),
                title = {
                    Text(
                        text = stringResource(id = R.string.choose_your_team),
                        fontFamily = Futura,
                        color = Color(0xFFFFFFFF),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        modifier = Modifier
                            .padding(
                                start = 32.dp
                            )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 1f * density
                        val y = size.height

                        drawLine(
                            Color(0xFF127A5B),
                            Offset(0f, y),
                            Offset(size.width, y),
//                        Offset(0f, 10f),
//                        Offset(10f, 20f),
                            20f
                        )
                    }
            )
        },
        content = {
            LazyColumn(
                contentPadding = PaddingValues(top = 11.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ){
                items(state.teams.size){i ->
                    TeamItemDetail(
                        state.teams[i],
                        onItemClick = {playerTeam ->
                            coroutineScope.launch {
                                viewModel.updatePlayerTeam(playerTeam)
                                navController.popBackStack()
                                navController.navigate(TabBarScreen.Main.route)//Screen.Home.route)
                            }
//                            navController.navigate(Screen.Home.route){
//                                popUpTo(Screen.Home.route){
//                                    inclusive = true
//                                }
//                            }
                        }
                    )
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun TeamChooserListPreview(
//){
//    val teams = teamMaker()
//    //val state = viewModel.state
//    Scaffold(
//        backgroundColor = Color(0xFF585858),
//        topBar = {
//            TopAppBar(
//                backgroundColor = Color(0xFF303030),
//                title = {
//                    Text(
//                        text = stringResource(id = R.string.choose_your_team),
//                        fontFamily = Futura,
//                        color = Color(0xFFFFFFFF),
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight(500),
//                        modifier = Modifier
//                            .padding(
//                                start = 32.dp
//                            )
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .drawBehind {
//                        val strokeWidth = 1f * density
//                        val y = size.height
//
//                        drawLine(
//                            Color(0xFF127A5B),
//                            Offset(0f, y),
//                            Offset(size.width, y),
////                        Offset(0f, 10f),
////                        Offset(10f, 20f),
//                            20f
//                        )
//                    }
//            )
//        },
//        content = {
//            LazyColumn(
//                contentPadding = PaddingValues(top = 11.dp),
//                verticalArrangement = Arrangement.spacedBy(4.dp),
//            ){
//                items(teams.size){ i ->
//                    TeamItemDetail(teams[i], {})
//                }
//            }
//        }
//    )
//}