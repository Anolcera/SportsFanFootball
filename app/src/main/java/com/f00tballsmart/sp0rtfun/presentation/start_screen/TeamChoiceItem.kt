package com.f00tballsmart.sp0rtfun.presentation.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.ui.theme.Futura
import com.f00tballsmart.sp0rtfun.ui.theme.Roboto

@Preview(showBackground = true)
@Composable
fun TeamItemDetailPreview(){
    val team = Team(
        name = "Feyenoord",
        "De Kuip",
        "Rotterdam",
        14,
        0,0,0,0,
    )
    TeamItemDetail(team = team, {})
}

@Composable
fun TeamItemDetail(
    team: Team,
    onItemClick: (Team) -> Unit
){
    val bullet = remember{"\u2022"}
    val messages = listOf(
        stringResource(id = R.string.info_city, team.city),
        stringResource(id = R.string.info_stadium, team.stadium)
    )
    val paragraphStyle = remember {
        ParagraphStyle(textIndent = TextIndent(restLine = 2.sp))
    }

    Card(
        backgroundColor = Color(0xFF303030),
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                start = 16.dp,
                end = 16.dp
            )
            .clickable {
                onItemClick(team)
            }
    ) {
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Min),
                //.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            Column(
                modifier = Modifier
                    .weight(3f, true),
                horizontalAlignment = Alignment.Start,
                //.wrapContentWidth(Alignment.Start)
            ) {
                Text(
                    text = team.name,
                    fontWeight = FontWeight(500),
                    fontFamily = Futura,
                    fontSize = 20.sp,
                    color = Color(0xFF, 0xFF, 0xFF),
                    modifier = Modifier
                        .padding(
                            start = 42.dp,
                            top = 20.dp,
                            bottom = 4.dp
                        )
                )

                Text(
                    buildAnnotatedString{
                        withStyle(SpanStyle(
                            fontFamily = Roboto,
                            fontSize = 12.sp,
                            color = Color(0xFF, 0xFF, 0xFF, 0x80),
                            fontWeight = FontWeight(400),
                        )){
                            messages.forEach {
                                withStyle(style = paragraphStyle) {
                                    append(bullet)
                                    append("\t\t")
                                    append(it)
                                }
                            }
                        }

                    },
                    modifier = Modifier
                        .padding(
                            start = 28.dp,
                            //bottom = 17.dp,
                            //end = 65.dp
                        )
                )

            }

            Divider(
                color = Color(0x26FFFFFF),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                    ),
            )

//            Divider(
//                color = Color.Red,
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .layout(){ measurable, constraints ->
//                        val placeable = measurable.measure(constraints.copy(
//                            maxWidth = 1, //add the end padding 16.dp
//                        ))
//                        layout(placeable.width, placeable.height) {
//                            placeable.place(0.dp.roundToPx(), 0)
//                        }
//                    }
//            )

            Image(
                painter = painterResource(id = team.bigRoundLogo),
                contentDescription = stringResource(id = R.string.team_logo_description),
                modifier = Modifier
                    .weight(2f, true)
                    //.size(84.dp)
//                    .height(84.dp)
//                    .width(84.dp),
                    //.fillMaxSize()
                    .padding(
                        start = 16.5.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
            )

        }
    }
}