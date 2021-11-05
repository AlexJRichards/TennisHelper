package com.example.tenhelper.data


import androidx.room.Entity
import androidx.room.PrimaryKey

//class for activity table
@Entity(tableName = "activity_table")
data class Activity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val distance_completed: Float
)