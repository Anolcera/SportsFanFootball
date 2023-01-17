package com.f00tballsmart.sp0rtfun.constants

sealed class Bets(val bet: String) {
    object HomeTeamWin: Bets("1")
    object Draw: Bets("X")
    object AwayTeamWin: Bets("2")
    object HomeTeamWinOrDraw: Bets("1X")
    object EitherTeamWin: Bets("12")
    object AwayTeamWinOrDraw: Bets("2X")
}

val BetsMap: Map<String, Bets> = mapOf(
    "1" to Bets.HomeTeamWin,
    "X" to Bets.Draw,
    "2" to Bets.AwayTeamWin,
    "1X" to Bets.HomeTeamWinOrDraw,
    "2X" to Bets.AwayTeamWinOrDraw,
    "12" to Bets.EitherTeamWin,
)