package com.example.tenhelper.data.repositories

import androidx.annotation.WorkerThread
import com.example.tenhelper.data.Player
import com.example.tenhelper.data.room.PlayerDao
import javax.inject.Inject

@WorkerThread
class PlayerRepository @Inject constructor (private val playerDao: PlayerDao) {

    suspend fun add(player: Player) {
        playerDao.insert(player)
    }

    suspend fun editPlayerName(id:Int, name:String){
        playerDao.updateName(id, name)
    }

    suspend fun editPlayerWeight(id:Int, weight:Int){
        playerDao.updateWeight(id, weight)
    }

    suspend fun editPlayerLevel(id:Int, level:Double){
        playerDao.updateRating(id, level)
    }

    suspend fun editPlayerPerWeek(id:Int, times:Int){
        playerDao.updateTimes(id, times)
    }

    suspend fun editPlayerFitness(id:Int, fitness:Int){
        playerDao.updateFitness(id, fitness)
    }

    //    suspend fun updateGoal(goal: Goal, goalID:Int) {
//        goalsDao.update(goal, goalID)
//    }
//    suspend fun deleteGoal(goal: Goal) {
//        goalsRoomDatabase.goalsDao().delete(goal)
//    }
//    suspend fun deleteAllGoals() {
//    }

    suspend fun getPlayerOne(id:Int) : Player {
        return playerDao.getPlayerWithId(id)
    }

    suspend fun getAll(): List<Player> {
        return playerDao.getAll()
    }

    suspend fun getPlayerById(id: Int): Player{
        return playerDao.getPlayerWithId(id)
    }
}
