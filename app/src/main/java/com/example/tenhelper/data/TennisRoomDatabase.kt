package com.example.tenhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tenhelper.data.room.FitnessDao
import com.example.tenhelper.data.room.GoalDao
import com.example.tenhelper.data.room.PlayerDao

@Database(entities = [Goal::class, FitnessPlan::class, Player::class], version = 1, exportSchema = false)
abstract class TennisRoomDatabase : RoomDatabase() {

    abstract fun tennisDao(): GoalDao
    abstract fun fitnessDao(): FitnessDao
    abstract fun playerDao(): PlayerDao
}