package com.f00tballsmart.sp0rtfun.util

import com.f00tballsmart.sp0rtfun.domain.model.Game
import com.f00tballsmart.sp0rtfun.domain.model.Team
import kotlin.math.ceil
import kotlin.random.Random

fun roundRobin(teams: List<Team>) : List<Game> {
    var firstHalfSeason : List<Game> = listOf()
    val secondHalfSeason : MutableList<Game> = mutableListOf()

    val half = ceil((teams.size / 2).toDouble()).toInt()
    val groupA : MutableList<Team> = teams.subList(0, half).toMutableList()
    val groupB : MutableList<Team> = teams.subList(half, teams.size).asReversed().toMutableList()

    firstHalfSeason = firstHalfSeason + getTour(groupA, groupB, 1)


    for(i in 1 until teams.size-1) {
        groupA.add(1, groupB.removeAt(0))
        groupB.add(groupA.removeLast())
        firstHalfSeason = firstHalfSeason + getTour(groupA, groupB, i+1)
    }

    var lastRound = firstHalfSeason.last().roundNumber
    var roundCounter = 0

    firstHalfSeason.forEachIndexed { index, team ->
        val game = team.swap()
        if(roundCounter == 0){
            lastRound++
        }
        roundCounter++
        if(roundCounter == half) {
            roundCounter = 0
        }
        game.roundNumber = lastRound
        secondHalfSeason.add(game)


    }

    return firstHalfSeason + secondHalfSeason
}


private fun getTour(groupA: List<Team>, groupB: List<Team>, tour: Int): List<Game> {
    val total : MutableList<Game> = mutableListOf()

    groupA.forEachIndexed { index, team ->
        var game = Game(homeTeam = team, awayTeam = groupB[index], roundNumber = tour)
        if (index == 0 && Random.nextBoolean()){
            game = game.swap()
        }
        total.add(game)
    }

    return total
}

private fun Game.swap(): Game {
    val homeTeam = this.awayTeam
    val awayTeam = this.homeTeam
    return Game(homeTeam, awayTeam, roundNumber = this.roundNumber)
}