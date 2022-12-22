package com.kudos.roominfocus.viewmodel

import androidx.lifecycle.ViewModel
import com.kudos.roominfocus.db.entity.Schedule
import com.kudos.roominfocus.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    suspend fun fullSchedule(): Flow<List<Schedule>> = mainRepository.getAll()

    suspend fun scheduleForStopName(name: String): Flow<List<Schedule>> =
        mainRepository.getByStopName(name)

}