package com.f00tballsmart.sp0rtfun.presentation.home_screen.table_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.repository.TeamsRepositoryImpl
import com.f00tballsmart.sp0rtfun.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableScreenViewModel @Inject constructor(
    teamsRepositoryImpl: TeamsRepositoryImpl
) : ViewModel(){

    var state by mutableStateOf(TableScreenState())

    init {
        viewModelScope.launch {
            teamsRepositoryImpl.getFootballTeams().collectLatest {result->
                when(result){
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(
                                teams = it.sortedByDescending {
                                    it.point
                                }
                            )
                        }
                    }
                    is Resource.Loading ->{
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }
}