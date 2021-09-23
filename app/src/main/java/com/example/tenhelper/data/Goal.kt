package com.example.tenhelper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class Goal (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goal_name: String,
    val goal_description: String,
    val goal_start:String,
    val goal_end:String,
    val goal_completed:Boolean
        )