package com.example.tenhelper.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tenhelper.data.Goal

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGoal(goal: Goal)

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("SELECT * from goal_table")
    suspend fun getAllGoals(): List<Goal>

    @Query("SELECT * from goal_table WHERE goal_completed = :t")
    suspend fun getCompletedGoals(t:Boolean): List<Goal>

    @Query("SELECT * from goal_table")
    fun getAllLive(): LiveData<List<Goal>>

    @Query("UPDATE goal_table SET goal_completed = :completed WHERE id = :goalID")
    suspend fun completedGoals(goalID: Int, completed:Boolean)

    @Query("SELECT * from goal_table where id = :id")
    fun getById(id: Int) : Goal?

    @Query("DELETE FROM goal_table")
    suspend fun deleteAllGoals()
}