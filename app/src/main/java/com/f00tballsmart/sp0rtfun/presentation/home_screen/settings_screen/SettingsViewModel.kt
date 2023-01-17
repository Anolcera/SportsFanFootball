package com.f00tballsmart.sp0rtfun.presentation.home_screen.settings_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import com.f00tballsmart.sp0rtfun.data.repository.TeamsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val teamsRepositoryImpl: TeamsRepositoryImpl
) : ViewModel(){

    var state by mutableStateOf(SettingsState())


    fun restart(){
        viewModelScope.launch {
            deleteDatabase()
        }

        viewModelScope.launch {
            deleteTournament()
        }

        viewModelScope.launch {
            deletePlayerTeam()
        }
    }

    private suspend fun deleteDatabase(){
        teamsRepositoryImpl.deleteTable()
    }

    private suspend fun deleteTournament(){
        preferencesRepository.deleteTournament()
    }

    private suspend fun deletePlayerTeam(){
        preferencesRepository.deletePlayerTeam()
    }

    fun toggleSoundSetting() {
        state = state.copy(
            soundChecked = !state.soundChecked
        )
    }

    fun toggleMusicSetting() {
        state = state.copy(
            musicChecked = !state.musicChecked
        )
    }

    fun toggleVibrationSetting() {
        state = state.copy(
            vibrationChecked = !state.vibrationChecked
        )
    }

    fun showDialog() {
        state = state.copy(
            showDialog = true
        )
    }

    fun dismissDialog() {
        state = state.copy(
            showDialog = false
        )
    }
}