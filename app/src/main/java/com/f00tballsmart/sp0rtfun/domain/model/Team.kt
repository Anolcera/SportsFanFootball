package com.f00tballsmart.sp0rtfun.domain.model

import androidx.annotation.DrawableRes
import com.f00tballsmart.sp0rtfun.constants.TeamLogosRound
import com.f00tballsmart.sp0rtfun.constants.TeamLogosRoundBig
import com.f00tballsmart.sp0rtfun.constants.TeamLogosSquare

data class Team(
    val name : String = "",
    val stadium : String = "",
    val city : String = "",
    val strength: Int = 0,
    var win: Int = 0,
    var lose: Int = 0,
    var draw: Int = 0,
    var point: Int = 0,
){
    private val teamSquareLogo = TeamLogosSquare()
    private val teamRoundLogo = TeamLogosRound()
    private val teamBigRoundLogo = TeamLogosRoundBig()
    //

    @get:DrawableRes val squareLogo: Int
        get() {
            return teamSquareLogo.logos[name] ?: throw IllegalAccessError(
                "No such team in TeamLogos"
            )
        }

    @get:DrawableRes val roundLogo: Int
        get() {
            return teamRoundLogo.logos[name] ?: throw IllegalAccessError(
                "No such team in TeamLogos"
            )
        }

    @get:DrawableRes val bigRoundLogo: Int
        get() {
            return teamBigRoundLogo.logos[name] ?: throw IllegalAccessError(
                "No such team in TeamLogos"
            )
        }
}
