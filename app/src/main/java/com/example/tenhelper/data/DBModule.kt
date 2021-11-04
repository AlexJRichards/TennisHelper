package com.example.tenhelper.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    // Code for database provider
    // taken from Stack Overflow post by jsonV (adapted)
    // accessed August 2021
    // https://stackoverflow.com/questions/63146318/how-to-create-and-use-a-room-database-in-kotlin-dagger-hilt


    @Singleton
    @Provides
    fun tennisDatabaseProvider(@ApplicationContext app: Context)
    = Room.databaseBuilder(
        app,
        TennisRoomDatabase::class.java,
        "tennis_database"
    )
        .createFromAsset("test.db") // populates database fitness plan from existing database file
        .build()
    //
    @Singleton
    @Provides
    fun goalDaoProvider(db: TennisRoomDatabase) = db.tennisDao()

    @Singleton
    @Provides
    fun fitnessDaoProvider(db: TennisRoomDatabase) = db.fitnessDao()

    @Singleton
    @Provides
    fun playerDaoProvider(db: TennisRoomDatabase) = db.playerDao()

    @Singleton
    @Provides
    fun activityDaoProvider(db: TennisRoomDatabase) = db.activityDao()
}