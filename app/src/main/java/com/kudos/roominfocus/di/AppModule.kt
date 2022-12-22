package com.kudos.roominfocus.di

import com.kudos.roominfocus.repository.MainRepository
import com.kudos.roominfocus.db.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(scheduleDao: ScheduleDao): MainRepository {
        return MainRepository(scheduleDao)
    }

}