package com.f00tballsmart.sp0rtfun.presentation.home_screen.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.local.GameEntity
import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.data.mapper.toGame
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel(){

    //private val preferencesRepository = PreferencesRepository.get()
    var state by mutableStateOf(MainScreenState())

    init {
        viewModelScope.launch {
            preferencesRepository.storedPlayerTeam.collectLatest {
                try {
                    val playerTeam = Gson().fromJson(it, PlayerTeam::class.java)
                    
                    state = state.copy(playerTeam = playerTeam)
                }catch (ex: Exception){
                    Log.e("Shplinti", "Failed to get stored player team", ex)
                }
            }
        }

        viewModelScope.launch {
            preferencesRepository.storedGames.collectLatest { it ->
                try {
                    val type = object : TypeToken<List<GameEntity>>() {}.type
                    val gameEntities = Gson().fromJson<List<GameEntity>>(it, type)
                    val tournament = gameEntities.map { it.toGame() }
                    val playerTeamName = state.playerTeam.teamName
                    
                    state = state.copy(
                        games = tournament,
                        playerTeamGames = tournament
                        .filter {
                            it.homeTeam.name == playerTeamName || it.awayTeam.name == playerTeamName
                        })
//                    state.playerTeamGames.forEach {
//                        
//                    }
                    
                }catch (ex: Exception){
                    Log.e("Shplinti", "Failed to get stored games", ex)
                }
            }
        }
    }
}