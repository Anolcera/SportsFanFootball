package com.f00tballsmart.sp0rtfun.domain.use_case.put_teams_in_db

import android.content.Context
import android.util.Log
import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.constants.TeamDetails

private const val TEAM_COUNT = 18

/*
private  val teamNames : List<Int> = listOf(
    TeamDetails.Ajax.teamName, TeamDetails.AZ.teamName, TeamDetails.Cambuur.teamName, TeamDetails.Emmen.teamName,
    TeamDetails.Excelsior.teamName, TeamDetails.Feyenoord.teamName, TeamDetails.FortunaSittard.teamName,
    TeamDetails.GoAheadEagles.teamName, TeamDetails.Groningen.teamName, TeamDetails.Heerenveen.teamName,
    TeamDetails.Nijmegen.teamName, TeamDetails.PSV.teamName, TeamDetails.Waalwijk.teamName,
    TeamDetails.SpartaRotterdam.teamName, TeamDetails.Twente.teamName, TeamDetails.Utrecht.teamName,
    TeamDetails.Vitesse.teamName, TeamDetails.Volendam.teamName,
)

private val cities: List<Int> = listOf(
    TeamDetails.Ajax.city, TeamDetails.AZ.city, TeamDetails.Cambuur.city, TeamDetails.Emmen.city,
    TeamDetails.Excelsior.city, TeamDetails.Feyenoord.city, TeamDetails.FortunaSittard.city,
    TeamDetails.GoAheadEagles.city, TeamDetails.Groningen.city, TeamDetails.Heerenveen.city,
    TeamDetails.Nijmegen.city, TeamDetails.PSV.city, TeamDetails.Waalwijk.city,
    TeamDetails.SpartaRotterdam.city, TeamDetails.Twente.city, TeamDetails.Utrecht.city, TeamDetails.Vitesse.city,
    TeamDetails.Volendam.city,
)

private val stadiums: List<Int> = listOf(
    TeamDetails.Ajax.stadium, TeamDetails.AZ.stadium, TeamDetails.Cambuur.stadium, TeamDetails.Emmen.stadium,
    TeamDetails.Excelsior.stadium, TeamDetails.Feyenoord.stadium, TeamDetails.FortunaSittard.stadium,
    TeamDetails.GoAheadEagles.stadium, TeamDetails.Groningen.stadium, TeamDetails.Heerenveen.stadium,
    TeamDetails.Nijmegen.stadium, TeamDetails.PSV.stadium, TeamDetails.Waalwijk.stadium,
    TeamDetails.SpartaRotterdam.stadium, TeamDetails.Twente.stadium, TeamDetails.Utrecht.stadium,
    TeamDetails.Vitesse.stadium, TeamDetails.Volendam.stadium,
)*/

private  val teamNames = listOf(
    "Ajax", "AZ", "SC Cambuur", "FC Emmen", "Excelsior", "Feyenoord", "Fortuna Sittard",
    "Go Ahead Eagles", "FC Groningen", "Heerenveen", "NEC Nijmegen", "PSV Eindhoven",
    "RKC Waalwijk", "Sparta Rotterdam", "FC Twente", "FC Utrecht", "Vitesse", "FC Volendam",
)

private val cities = listOf(
    "Amsterdam", "Alkmaar", "Leeuwarden", "Emmen", "Rotterdam", "Rotterdam", "Sittard",
    "Deventer", "Groningen", "Heerenveen", "Nijmegen", "Eindhoven", "Waalwijk",
    "Rotterdam", "Enschede", "Utrecht", "Arnhem", "Volendam",
)

private val stadiums = listOf(
    "Johan Cruyff Arena", "AFAS Stadion", "Cambuur Stadion", "De Oude Meerdijk",
    "Van Donge & De Roo Stadion", "De Kuip", "Fortuna Sittard Stadion", "De Adelaarshorst",
    "Euroborg", "Abe Lenstra Stadium", "Goffertstadion", "Philips Stadion", "Mandemakers Stadion",
    "Spartastadion Het Kasteel", "De Grolsch Veste", "Stadion Galgenwaard", "GelreDome", "Kras Stadion",
)

private val strengths = listOf(
    10, 9, 7, 6, 4, 10, 5, 6, 7, 5, 5, 3, 4, 6, 7, 7, 6, 2
)

fun teamMaker() : List<Team>{
    if (
        TEAM_COUNT != teamNames.size ||
        TEAM_COUNT != cities.size ||
        TEAM_COUNT != stadiums.size ||
        TEAM_COUNT != strengths.size
    ){
        throw java.lang.IndexOutOfBoundsException(
            "Some lists have different list sizes or they doesn't equal to $TEAM_COUNT"
        )
    }

    val teams = mutableListOf<Team>()
    for(i in 0 until TEAM_COUNT){
        teams.add(
            i,
            Team(
                name = teamNames[i],
                city = cities[i].toString(),
                stadium = stadiums[i].toString(),
                strength = strengths[i],
                draw = 0,
                lose = 0,
                point = 0,
                win = 0,
            )
        )
    }
    
    return teams
}