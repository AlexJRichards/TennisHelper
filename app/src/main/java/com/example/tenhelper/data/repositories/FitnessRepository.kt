package com.example.tenhelper.data.repositories

import androidx.annotation.WorkerThread
import com.example.tenhelper.data.FitnessPlan
import com.example.tenhelper.data.room.FitnessDao
import javax.inject.Inject

@WorkerThread
class FitnessRepository @Inject constructor (private val fitnessDao: FitnessDao) {

    suspend fun add(fitnessPlan: FitnessPlan) {
        fitnessDao.insert(fitnessPlan)
    }

    suspend fun markCompleted(id:Int){
        fitnessDao.markCompleted(id, true)
    }

    suspend fun markIncompleted(id:Int){
        fitnessDao.markCompleted(id, false)
    }

    suspend fun adjustLength(num:Double, id:Int){
        fitnessDao.updateLength(num, id)
    }

    suspend fun getAll(): List<FitnessPlan> {
        return fitnessDao.getAll()
    }

    suspend fun editDistance(id:Int, distance:Int){
        fitnessDao.updateDistance(id, distance)
    }
}
