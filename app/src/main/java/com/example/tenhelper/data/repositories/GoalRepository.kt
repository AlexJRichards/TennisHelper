package com.example.tenhelper.data.repositories

import androidx.annotation.WorkerThread
import com.example.tenhelper.data.Goal
import com.example.tenhelper.data.room.GoalDao
import javax.inject.Inject

@WorkerThread
class GoalRepository @Inject constructor (private val goalDao: GoalDao) {

    suspend fun addGoal(goal: Goal) {
        goalDao.insertGoal(goal)
    }

    suspend fun markCompleted(goalID:Int){
        goalDao.completedGoals(goalID, true)
    }

    suspend fun markUncompleted(goalID:Int){
        goalDao.completedGoals(goalID, false)
    }

    suspend fun getCompletedGoals(): List<Goal> {
        return goalDao.getCompletedGoals(true)
    }

//    suspend fun updateGoal(goal: Goal, goalID:Int) {
//        goalsDao.update(goal, goalID)
//    }
//    suspend fun deleteGoal(goal: Goal) {
//        goalsRoomDatabase.goalsDao().delete(goal)
//    }
//    suspend fun deleteAllGoals() {
//    }
    suspend fun getAllGoals(): List<Goal> {
        return goalDao.getAllGoals()
    }




}