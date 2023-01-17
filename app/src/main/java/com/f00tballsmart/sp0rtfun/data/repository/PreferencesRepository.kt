package com.f00tballsmart.sp0rtfun.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.data.local.TeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    val storedPlayerTeam: Flow<String> = dataStore.data.map {
        it[PLAYER_TEAM] ?:""
    }.distinctUntilChanged()

    val storedGames: Flow<String> = dataStore.data.map{
        it[GAMES_ENTITY] ?: ""
    }.distinctUntilChanged()


    suspend fun updatePlayerTeam(playerTeam: String){
        dataStore.edit {
            it[PLAYER_TEAM] = playerTeam
        }
    }

    suspend fun updateGames(games: String){
        dataStore.edit {
            it[GAMES_ENTITY] = games
        }
    }

    suspend fun deleteTournament(){
        dataStore.edit {
            it[GAMES_ENTITY] = ""
        }
    }

    suspend fun deletePlayerTeam(){
        dataStore.edit {
            it[PLAYER_TEAM] = ""
        }
    }

    companion object{
        private val PLAYER_TEAM = stringPreferencesKey("player_team")
        private val GAMES_ENTITY = stringPreferencesKey("game_entity")

//        private var INSTANCE: PreferencesRepository? = null
//
//        fun initialize(context: Context) {
//            if (INSTANCE == null) {
//                val dataStore = PreferenceDataStoreFactory.create {
//                    context.preferencesDataStoreFile("settings")
//                }
//                INSTANCE = PreferencesRepository(dataStore)
//            }
//        }
//        fun get(): PreferencesRepository {
//            return INSTANCE ?: throw IllegalStateException(
//                "PreferencesRepository must be initialized"
//            )
//        }
    }
}