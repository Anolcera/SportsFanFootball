package com.f00tballsmart.sp0rtfun.data.local

import androidx.room.*

@Dao
interface TeamsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTeams(
        teamsInfoEntity: List<TeamEntity>
    )

    @Query("SELECT * FROM TeamEntity")
    suspend fun getTeams(): List<TeamEntity>

    @Query("SELECT * FROM TeamEntity WHERE name=(:name)")
    suspend fun getTeam(name: String) : TeamEntity

    @Update
    suspend fun updateTeam(team: TeamEntity)

    @Query("DELETE FROM TeamEntity")
    suspend fun deleteAll()
}