package com.f00tballsmart.sp0rtfun.data.repository

import com.f00tballsmart.sp0rtfun.data.local.TeamDatabase
import com.f00tballsmart.sp0rtfun.data.mapper.toTeam
import com.f00tballsmart.sp0rtfun.data.mapper.toTeamEntity
import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.domain.repository.TeamsRepository
import com.f00tballsmart.sp0rtfun.domain.use_case.put_teams_in_db.teamMaker
import com.f00tballsmart.sp0rtfun.util.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsRepositoryImpl @Inject constructor(
    private val database: TeamDatabase
) : TeamsRepository{

    private val dao = database.dao

    override suspend fun insertFootballTeams(teamsInfo: List<Team>) {
        dao.insertTeams(teamsInfo.map {it ->
            it.toTeamEntity()
        })
    }

    override suspend fun getFootballTeams(): Flow<Resource<List<Team>>> {
        return flow{
            emit(Resource.Loading(true))
            var teamListings = dao.getTeams()

            if(teamListings.isEmpty()){
                emit(Resource.Error("Table is empty"))
            }else{
                emit(Resource.Success(
                    data = teamListings.map {it ->
                        it.toTeam()
                    }
                ))
            }
        }
    }

    override suspend fun getFootballTeam(name: String): Team {
        return dao.getTeam(name).toTeam()
    }

    override suspend fun updateTeam(team: Team) {
        dao.updateTeam(team.toTeamEntity())
    }

    override suspend fun deleteTable(){
        dao.deleteAll()
    }

}