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
//
//    companion object {
//        @Volatile
//        private var INSTANCE: GoalsRoomDatabase? = null
//
//        fun getDatabase(context: Context): GoalsRoomDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    GoalsRoomDatabase::class.java,
//                    "goals_database"
//                )
//                    // Wipes and rebuilds instead of migrating if no Migration object.
//                    // Migration is not part of this codelab.
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
}