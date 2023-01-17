package com.f00tballsmart.sp0rtfun.data.local

data class PlayerTeam(
    val teamName: String = "",
    val stadium: String = "",
    val city: String = "",
    val strength: Int = 0,
    var point: Int = 0,
    var win: Int = 0,
    var lose: Int = 0,
    var draw: Int = 0,
    var averageTime: Float = 0f,
    var winOdds: Int = 0,
    var loseOdds: Int = 0,
    var playedRound : Int = 0,
)
