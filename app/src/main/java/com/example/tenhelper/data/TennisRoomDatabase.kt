package com.example.tenhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tenhelper.data.room.ActivityDao
import com.example.tenhelper.data.room.FitnessDao
import com.example.tenhelper.data.room.GoalDao
import com.example.tenhelper.data.room.PlayerDao

//Hilt database class
@Database(entities = [Goal::class, FitnessPlan::class, Player::class, Activity::class], version = 1, exportSchema = false)
abstract class TennisRoomDatabase : RoomDatabase() {
    // Links to data access objects
    abstract fun tennisDao(): GoalDao
    abstract fun fitnessDao(): FitnessDao
    abstract fun playerDao(): PlayerDao
    abstract fun activityDao(): ActivityDao
}