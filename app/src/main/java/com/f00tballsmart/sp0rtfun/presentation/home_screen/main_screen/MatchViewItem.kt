package com.f00tballsmart.sp0rtfun.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.Bets
import com.f00tballsmart.sp0rtfun.domain.model.Game


@Composable
fun MatchViewItem(
    game: Game,
    onOddsClick: (Bets) -> Unit,
){
    Card(
        backgroundColor = Color(0xFF585858),
        shape = RectangleShape,
        modifier = Modifier
            .padding(
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth()
            .height(108.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(61.5.dp)
            ) {

                val homeTeam = game.homeTeam
                val awayTeam = game.awayTeam
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .weight(2f)
                        .padding(
                            top = 16.dp,
                            start = 12.dp,
                            bottom = 5.5.dp
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Image(
                            painter = painterResource(id = homeTeam.roundLogo),
                            contentDescription = stringResource(R.string.home_team_logo_description),
                            modifier = Modifier
                                .weight(1f)
//                                .padding(
//                                    start = 12.dp,
//                                    top = 16.dp,
//                                    bottom = 5.5.dp
//                                )
                        )

                        Text(
                            text = homeTeam.name,
                            textAlign = TextAlign.Center,
                            color = Color(0xB3FFFFFF),
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                )
                                .weight(2f)
                        )
                    }
                }

                Text(
                    text = "${game.homeScore} : ${game.awayScore}",
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFBEB64),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = 25.dp
                        )
                )

                Box(
                    //contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .weight(2f)
                        .padding(
                            top = 16.dp,
                            end = 12.dp,
                            bottom = 5.5.dp
                        )
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = awayTeam.name,
                            textAlign = TextAlign.Center,
                            color = Color(0xB3FFFFFF),
                            modifier = Modifier
                                .padding(
                                    end = 8.dp,
                                )
                                .weight(2f)
                        )

                        Image(
                            painter = painterResource(id = awayTeam.roundLogo),
                            contentDescription = stringResource(R.string.away_team_logo_description),
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }

            Divider(
                color = Color(0xFF127A5B),
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp
                    )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 6.dp,
                        bottom = 8.dp
                    ),
            ) {
                OddsViewItem(Bets.HomeTeamWin, game.homeTeamWinOdd, onOddsClick)
                OddsViewItem(Bets.Draw, game.drawOdd, onOddsClick)
                OddsViewItem(Bets.AwayTeamWin, game.awayTeamWinOdd, onOddsClick)
                OddsViewItem(Bets.HomeTeamWinOrDraw, game.homeTeamWinOrDrawOdd, onOddsClick)
                OddsViewItem(Bets.EitherTeamWin, game.eitherWinOdd, onOddsClick)
                OddsViewItem(Bets.AwayTeamWinOrDraw, game.awayTeamWinOrDraw, onOddsClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun OddsViewItem(
    chance: Bets,
    odd: Float,
    onOddsClick: (Bets) -> Unit,
){

    Card(
        backgroundColor = Color(0xFF404040),
        shape = RectangleShape,
        modifier = Modifier
            .width(48.dp)
            .height(32.dp),
        onClick = {
            onOddsClick(chance)
        }
    ) {

        Column(
        ) {
            Text(
                text = chance.bet,
                fontSize = 11.sp,
                color = Color(0xB3FBEB64),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = odd.toString(),
                fontSize = 11.sp,
                color = Color(0xB3FFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}


//@Preview(showBackground = true)
//@Composable
//fun MatchViewItemPreview(){
//    val homeTeam = Team(
//        name = stringResource(id = TeamDetails.SpartaRotterdam.teamName),
//        stadium = "",
//        city = "",
//        strength = 10,
//        win = 0,
//        lose = 0,
//        draw = 0,
//        point = 0,
//    )
//
//    val awayTeam = Team(
//        name = stringResource(id = TeamDetails.GoAheadEagles.teamName),
//        stadium = "",
//        city = "",
//        strength = 10,
//        win = 0,
//        lose = 0,
//        draw = 0,
//        point = 0,
//    )
//
//    val game = Game(
//        homeTeam = homeTeam,
//        awayTeam = awayTeam,
//        homeScore = 0,
//        awayScore = 3,
//        roundNumber = 0,
//    )
//    MatchViewItem(game, {})
//}