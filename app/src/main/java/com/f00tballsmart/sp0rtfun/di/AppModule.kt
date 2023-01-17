package com.f00tballsmart.sp0rtfun.di

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.f00tballsmart.sp0rtfun.data.local.TeamDatabase
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTeamDatabase(app: Application): TeamDatabase{
        return Room.databaseBuilder(
            app,
            TeamDatabase::class.java,
            "teamdb.db"
        ).build()
    }


    @Provides
    @Singleton
    fun providePreferencesRepository(app: Application) : PreferencesRepository{
        app.applicationContext
        return PreferencesRepository(
            PreferenceDataStoreFactory.create {
                app.applicationContext.preferencesDataStoreFile("settings")
            }
        )
    }
}