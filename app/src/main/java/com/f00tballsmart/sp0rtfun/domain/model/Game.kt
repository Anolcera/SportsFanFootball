package com.f00tballsmart.sp0rtfun.domain.model

import com.f00tballsmart.sp0rtfun.constants.*
import kotlin.math.abs

data class Game(
    var homeTeam: Team = Team(),
    var awayTeam: Team = Team(),
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    var roundNumber: Int = 0,
){
    val strengthDifference: Int
        get(){
            return homeTeam.strength - awayTeam.strength
        }

    val homeTeamWinOdd : Float
        get() {
            if (strengthDifference < 0){
                return weakTeamWinOdds().odds[abs(strengthDifference)]!!
            }else if(strengthDifference == 0){
                return weakTeamWinOdds().odds[0]!!
            }else{
                return strongTeamWinOdds().odds[abs(strengthDifference)]!!
            }
        }

    val drawOdd: Float
        get() {
            return drawOdds().odds[abs(strengthDifference)]!!
        }

    val awayTeamWinOdd: Float
        get() {
            if (strengthDifference < 0){
                return strongTeamWinOdds().odds[abs(strengthDifference)]!!
            }else if(strengthDifference == 0){
                return strongTeamWinOdds().odds[0]!!
            }else{
                return weakTeamWinOdds().odds[abs(strengthDifference)]!!
            }
        }

    val eitherWinOdd : Float
        get() {
            return eitherWinOdds().odds[abs(strengthDifference)]!!
        }

    val homeTeamWinOrDrawOdd : Float
        get(){
            if (strengthDifference < 0){
                return weakTeamWinOrDrawOdds().odds[abs(strengthDifference)]!!
            }else if(strengthDifference == 0){
                return weakTeamWinOrDrawOdds().odds[0]!!
            }else{
                return strongTeamWinOrDrawOdds().odds[abs(strengthDifference)]!!
            }
        }

    val awayTeamWinOrDraw : Float
        get(){
            if (strengthDifference < 0){
                return strongTeamWinOrDrawOdds().odds[abs(strengthDifference)]!!
            }else if(strengthDifference == 0){
                return strongTeamWinOrDrawOdds().odds[0]!!
            }else{
                return weakTeamWinOrDrawOdds().odds[abs(strengthDifference)]!!
            }
        }
}
