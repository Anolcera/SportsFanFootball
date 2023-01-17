package com.f00tballsmart.sp0rtfun.presentation.start_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.mapper.toGameEntity
import com.f00tballsmart.sp0rtfun.data.mapper.toPlayerTeam
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import com.f00tballsmart.sp0rtfun.data.repository.TeamsRepositoryImpl
import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.domain.use_case.put_teams_in_db.teamMaker
import com.f00tballsmart.sp0rtfun.util.Resource
import com.f00tballsmart.sp0rtfun.util.roundRobin
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val teamsRepository: TeamsRepositoryImpl,
    private val preferencesRepository : PreferencesRepository
) : ViewModel(){

    //private val preferencesRepository = PreferencesRepository.get()
    var state by mutableStateOf(ChooseTeamsState())

    init {
        
        viewModelScope.launch {
            state = state.copy(
                statusBarColor = Color(0xFF303030)
            )
            grabData()
        }
    }

    private suspend fun grabData(){
        teamsRepository.getFootballTeams().collect{result ->
            when(result){
                is Resource.Success -> {
                    result.data?.let {
                        state = state.copy(
                            teams = it
                        )
                        try{
                            val tournament = roundRobin(it).map {
                                it.toGameEntity()
                            }
                            tournament.forEach {
                                    
                                }
                            val gamesToJson: String = Gson().toJson(tournament)
                            preferencesRepository.deleteTournament()
                            preferencesRepository.updateGames(gamesToJson)
                        }catch (ex: Exception){
                            Log.e("Shplinti", "updateGames can't be done in ChooseTeamViewModel", ex)
                        }

                    }
                }
                is Resource.Loading ->{
                    state = state.copy(isLoading = true)
                }
                is Resource.Error -> {
                    val teams = teamMaker()
                    teamsRepository.insertFootballTeams(teams)
                    grabData()
                    
                }
            }
        }
    }

    suspend fun updatePlayerTeam(playerTeam: Team){
        val playerTeamToJson = Gson().toJson(playerTeam.toPlayerTeam())
        try{
            preferencesRepository.deletePlayerTeam()
            preferencesRepository.updatePlayerTeam(playerTeamToJson)

        }catch (ex: Exception){
            
        }
    }
}