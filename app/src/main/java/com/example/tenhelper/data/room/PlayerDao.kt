package com.example.tenhelper.data.room

import androidx.room.*
import com.example.tenhelper.data.Player

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("SELECT * from player_table where id = :id")
    suspend fun getPlayerWithId(id:Int): Player

    @Query("SELECT * from player_table")
    suspend fun getAll(): List<Player>

    @Query("DELETE FROM player_table")
    suspend fun deletePlayer()

    @Query("UPDATE player_table SET player_name = :name WHERE id = :id")
    suspend fun updateName(id: Int, name:String)

    @Query("UPDATE player_table SET weight = :weight WHERE id = :id")
    suspend fun updateWeight(id: Int, weight:Int)

    @Query("UPDATE player_table SET tennis_rating = :rating WHERE id = :id")
    suspend fun updateRating(id: Int, rating:Double)

    @Query("UPDATE player_table SET fitness = :fitnessRating WHERE id = :id")
    suspend fun updateFitness(id: Int, fitnessRating: Int)

    @Query("UPDATE player_table SET times_per_week = :times WHERE id = :id")
    suspend fun updateTimes(id: Int, times:Int)

}