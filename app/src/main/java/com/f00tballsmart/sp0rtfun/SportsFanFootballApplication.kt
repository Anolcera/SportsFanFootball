package com.f00tballsmart.sp0rtfun

import android.app.Application
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SportsFanFootballApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //PreferencesRepository.initialize(this)
    }
}