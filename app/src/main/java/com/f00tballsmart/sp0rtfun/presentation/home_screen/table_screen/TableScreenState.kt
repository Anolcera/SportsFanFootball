package com.f00tballsmart.sp0rtfun.presentation.home_screen.table_screen

import com.f00tballsmart.sp0rtfun.domain.model.Team

data class TableScreenState(
    val teams: List<Team> = emptyList(),
    val isLoading: Boolean = false,
)