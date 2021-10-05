package com.example.tenhelper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val player_name: String,
    val height: Int,
    val weight: Int,
    val tennis_rating:Double,
    val fitness:Int,
    val gender:String,
    val times_per_week:Int,
)