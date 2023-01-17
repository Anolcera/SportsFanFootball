package com.f00tballsmart.sp0rtfun.presentation.start_screen

import androidx.compose.ui.graphics.Color
import com.f00tballsmart.sp0rtfun.domain.model.Team

data class ChooseTeamsState(
    val teams: List<Team> = emptyList(),
    val isLoading: Boolean = false,
    val statusBarColor : Color = Color(0x000000),
)
