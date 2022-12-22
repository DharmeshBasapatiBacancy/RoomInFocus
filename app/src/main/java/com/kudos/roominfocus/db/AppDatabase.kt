package com.kudos.roominfocus.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudos.roominfocus.db.dao.ScheduleDao
import com.kudos.roominfocus.db.entity.Schedule

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

}