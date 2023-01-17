package com.f00tballsmart.sp0rtfun.domain.repository

import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TeamsRepository {

    suspend fun insertFootballTeams(
        teamsInfo: List<Team>
    )

    suspend fun getFootballTeams() : Flow<Resource<List<Team>>>

    suspend fun getFootballTeam(name: String): Team

    suspend fun updateTeam(teams: Team)

    suspend fun deleteTable()
}