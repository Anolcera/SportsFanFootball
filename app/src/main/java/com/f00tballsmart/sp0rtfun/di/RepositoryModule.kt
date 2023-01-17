package com.f00tballsmart.sp0rtfun.di

import com.f00tballsmart.sp0rtfun.data.repository.TeamsRepositoryImpl
import com.f00tballsmart.sp0rtfun.domain.repository.TeamsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTeamsRepository(
        teamsRepositoryImpl: TeamsRepositoryImpl
    ): TeamsRepository

//    @Binds
//    @Singleton
//    abstract fun bindPreferencesRepository(
//        preferencesRepository: PreferencesRepository
//    ): PreferencesRepository
}