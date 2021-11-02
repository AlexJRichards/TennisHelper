package com.example.tenhelper.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tenhelper.data.Activity
import com.example.tenhelper.data.Goal

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActivity(activity: Activity)

    @Update
    suspend fun updateActivity(activity: Activity)

    @Query("SELECT * from activity_table")
    suspend fun getAll(): List<Activity>
}