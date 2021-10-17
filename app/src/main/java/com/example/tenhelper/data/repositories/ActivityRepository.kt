package com.example.tenhelper.data.repositories

import androidx.annotation.WorkerThread
import com.example.tenhelper.data.Activity
import com.example.tenhelper.data.FitnessPlan
import com.example.tenhelper.data.room.ActivityDao
import com.example.tenhelper.data.room.FitnessDao
import javax.inject.Inject

@WorkerThread
class ActivityRepository @Inject constructor (private val activityDao: ActivityDao) {

    suspend fun add(activity: Activity) {
        activityDao.addActivity(activity)
    }

    suspend fun getAll(): List<Activity> {
        return activityDao.getAll()
    }
}