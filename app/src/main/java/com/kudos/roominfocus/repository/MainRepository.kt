package com.kudos.roominfocus.repository

import com.kudos.roominfocus.db.dao.ScheduleDao
import com.kudos.roominfocus.db.entity.Schedule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val scheduleDao: ScheduleDao) {

    fun getAll(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun getByStopName(stopName: String): Flow<List<Schedule>> =
        scheduleDao.getByStopName(stopName)
}