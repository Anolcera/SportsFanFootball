package com.f00tballsmart.sp0rtfun.data.mapper

import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.data.local.TeamEntity
import com.f00tballsmart.sp0rtfun.domain.model.Team

fun TeamEntity.toTeam(): Team{
    return Team(
        name = name,
        stadium = stadium,
        city = city,
        strength = strength,
        win = win,
        lose = lose,
        draw = draw,
        point = point,
    )
}

fun Team.toTeamEntity(): TeamEntity{
    return TeamEntity(
        name = name,
        stadium = stadium,
        city = city,
        strength = strength,
        win = win,
        lose = lose,
        draw = draw,
        point = point,
    )
}

fun Team.toPlayerTeam(): PlayerTeam{
    return PlayerTeam(
        teamName = name,
        stadium = stadium,
        city = city,
        strength = strength,
        win = win,
        point = point,
        lose = lose,
        draw = draw,
        averageTime = 0f,
        winOdds = 0,
        loseOdds = 0,
    )
}

fun PlayerTeam.toTeam() : Team{
    return Team(
        name = teamName,
        stadium = stadium,
        city = city,
        strength = strength,
        win = win,
        lose = lose,
        draw = draw,
        point = point,
    )
}