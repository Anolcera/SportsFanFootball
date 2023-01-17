package com.f00tballsmart.sp0rtfun.data.mapper

import com.f00tballsmart.sp0rtfun.data.local.GameEntity
import com.f00tballsmart.sp0rtfun.domain.model.Game

fun GameEntity.toGame() : Game {
    return Game(
        homeTeam = homeTeam.toTeam(),
        awayTeam = awayTeam.toTeam(),
        homeScore = homeScore,
        awayScore = awayScore,
        roundNumber = roundNumber,
    )
}

fun Game.toGameEntity() : GameEntity {
    return GameEntity(
        homeTeam = homeTeam.toTeamEntity(),
        awayTeam = awayTeam.toTeamEntity(),
        homeScore = homeScore,
        awayScore = awayScore,
        roundNumber = roundNumber,
    )
}