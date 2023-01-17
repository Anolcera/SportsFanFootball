package com.f00tballsmart.sp0rtfun.presentation.home_screen.stats_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsScreenViewModel @Inject constructor(
    private val preferencesRepository : PreferencesRepository
) : ViewModel(){

    //private val preferencesRepository = PreferencesRepository.get()
    var state by mutableStateOf(StatsScreenState())

    init{
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
    }
}