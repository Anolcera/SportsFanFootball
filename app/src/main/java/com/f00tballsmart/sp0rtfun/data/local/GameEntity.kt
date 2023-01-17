package com.f00tballsmart.sp0rtfun.data.local


data class GameEntity(
    val homeTeam: TeamEntity,
    val awayTeam: TeamEntity,
    var homeScore: Int,
    var awayScore: Int,
    var roundNumber: Int,
)