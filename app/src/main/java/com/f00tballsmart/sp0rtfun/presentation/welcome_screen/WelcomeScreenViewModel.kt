package com.f00tballsmart.sp0rtfun.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val preferencesRepository : PreferencesRepository
) : ViewModel(){

    //private val preferencesRepository = PreferencesRepository.get()
    private val _state : MutableStateFlow<WelcomeScreenState> = MutableStateFlow(WelcomeScreenState())
    val state: StateFlow<WelcomeScreenState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.storedPlayerTeam.collectLatest {
                if (it.isBlank()){
                    _state.update {
                        it.copy(shouldShowStartScreen = true)
                    }
                }else{
                    _state.update {
                        it.copy(shouldShowStartScreen = false)
                    }
                }
            }
        }

    }
}