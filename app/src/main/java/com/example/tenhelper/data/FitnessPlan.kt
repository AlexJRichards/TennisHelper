package com.example.tenhelper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
data class FitnessPlan (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val week: Int,
    val day: Int,
    val activityName:String,
    val activityType:String,
    val activityLength:String,
    val activitySets:String,
    val activityDescription:String,
    val completed:Boolean?,
    val activityCompleted:String
)