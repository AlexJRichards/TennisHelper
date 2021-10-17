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

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun tennisDatabaseProvider(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TennisRoomDatabase::class.java,
        "tennis_database"
    )
        .createFromAsset("test.db") // populates database fitness plan from existing database file
        .build()

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