package com.example.tenhelper.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tenhelper.data.FitnessPlan

@Dao
interface FitnessDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fitnessPlan: FitnessPlan)

    @Update
    suspend fun update(fitnessPlan: FitnessPlan)

    @Delete
    suspend fun delete(fitnessPlan: FitnessPlan)

    @Query("SELECT * from plan_table")
    suspend fun getAll(): List<FitnessPlan>

    @Query("SELECT * from plan_table")
    fun getAllLive(): LiveData<List<FitnessPlan>>

    @Query("SELECT * from plan_table where id = :id")
    fun getById(id: Int) : FitnessPlan?

    @Query("DELETE FROM plan_table")
    suspend fun deleteAll()

    @Query("UPDATE plan_table SET activityLength = :newLength WHERE id = :ID")
    suspend fun updateLength(newLength:Double, ID: Int)

    @Query("UPDATE plan_table SET activityCompleted = :length WHERE id = :ID")
    suspend fun updateDistance(length:Int, ID: Int)

    @Query("UPDATE plan_table SET activitySets = :newSets WHERE id = :ID")
    suspend fun updateSet(newSets:Int, ID: Int)

    @Query("UPDATE plan_table SET completed = :completed WHERE id = :ID")
    suspend fun markCompleted(ID: Int, completed:Boolean)
}