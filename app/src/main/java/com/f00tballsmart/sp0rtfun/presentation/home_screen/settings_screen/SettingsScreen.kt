package com.f00tballsmart.sp0rtfun.presentation.home_screen.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.presentation.home_screen.TopBar
import com.f00tballsmart.sp0rtfun.presentation.navigation.Screen

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController,
){
    val state = viewModel.state
    val onSoundClicked: () -> Unit = {
        viewModel.toggleSoundSetting()
    }

    val onMusicClicked: () -> Unit = {
        viewModel.toggleMusicSetting()
    }

    val onVibrationClicked: () -> Unit = {
        viewModel.toggleVibrationSetting()
    }

   Scaffold(
       topBar = { TopBar() },
       backgroundColor = Color(0xFF404040)
   ) {
       Column {
           SettingsCard(settingsOption = "Sound", state.soundChecked, onSoundClicked)
           SettingsCard(settingsOption = "Music", state.musicChecked, onMusicClicked)
           SettingsCard(settingsOption = "Vibration", state.vibrationChecked, onVibrationClicked)

           Button(
               colors = ButtonDefaults.buttonColors(Color.Yellow),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 74.dp, start = 70.dp, end = 70.dp),
               onClick = { viewModel.showDialog() }) {
               Box(
                   contentAlignment = Alignment.Center,
//                   modifier = Modifier
//                       .fillMaxSize()
               ) {
                   Text(
                       text = "Restart season",
                       color = Color.Black,
                       fontSize = 15.sp,
                       fontWeight = FontWeight(500)
                   )
               }
           }
       }

       if (state.showDialog){
           WarningDialog(viewModel, navController)
       }
   }
}

@Composable
private fun WarningDialog(
    viewModel: SettingsViewModel,
    navController: NavController// = rememberNavController()
){
    Dialog(onDismissRequest = {viewModel.dismissDialog()}) {
        Box(
            //shape = RectangleShape,
            //backgroundColor = Color.Black,
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .width(320.dp)
                .height(190.dp)
                .background(Color.Black)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "All your progress will be deleted.\nAre you sure?",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 40.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 27.dp, end = 27.dp)
                ) {

                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        onClick = {
                            viewModel.restart()
                            navController.navigate(Screen.Start.route)
                            {
                                popUpTo(Screen.Start.route){
                                    inclusive = true
                                }
                            }
                        }) {
                        Box(
                            contentAlignment = Alignment.Center,
//                   modifier = Modifier
//                       .fillMaxSize()
                        ) {
                            Text(
                                text = "Yes",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier
                        .width(21.dp)
                        .weight(1f))

                    Button(
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        onClick = { viewModel.dismissDialog() }) {
                        Box(
                            contentAlignment = Alignment.Center,
//                   modifier = Modifier
//                       .fillMaxSize()
                        ) {
                            Text(
                                text = "No",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsCard(
    settingsOption: String,
    checked: Boolean,
    onToggleClicked: () -> Unit,
){
    Box(
        //shape = RectangleShape,
        //backgroundColor = Color(0xFF585858),
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(bottom = 2.dp)
            .background(Color(0xFF585858))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 36.dp,
                    end = 41.dp
                )
        ) {
            Text(
                text = settingsOption,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight(500)
            )

            ToggleSwitch(checked, onToggleClicked)
        }
    }
}

@Composable
private fun ToggleSwitch(
    checked: Boolean,
    onToggleClicked: () -> Unit,
){
    val icon = if (checked) R.drawable.switch_on else R.drawable.switch_off

    Icon(
        painter = painterResource(id = icon),
        contentDescription = "",
        tint = Color.Unspecified,
        modifier = Modifier
            .clickable {
                onToggleClicked()
            }
    )
}