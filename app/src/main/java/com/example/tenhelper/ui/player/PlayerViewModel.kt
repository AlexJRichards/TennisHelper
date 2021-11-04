package com.example.tenhelper.ui.player

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tenhelper.data.Player
import com.example.tenhelper.data.repositories.PlayerRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val playerRepository: PlayerRepository) : ViewModel(){
    var players: MutableState<List<Player>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            players.value = playerRepository.getAll()
        }
    }

    suspend fun get() : List<Player> {
        return playerRepository.getAll()
    }

    suspend fun getPlayerById(id:Int):Player{
        return playerRepository.getPlayerById(id)
    }

    fun addPlayer(
        id: Int = 0,
        player_name: String,
        height: Int,
        weight: Int,
        tennis_rating:Double,
        fitness: Int,
        gender: String,
        times_per_week:Int
    ){
        viewModelScope.launch {
            playerRepository.add(Player(id,player_name,height,weight,tennis_rating,fitness, gender, times_per_week))
        }
    }

    fun editPlayer(
        id: Int,
        player_name: String,
        height: Int,
        weight: Int,
        tennis_rating:Double,
        fitness: Int,
        times_per_week:Int
    ){
        viewModelScope.launch {
            playerRepository.editPlayerLevel(id, tennis_rating)
            playerRepository.editPlayerName(id, player_name)
            playerRepository.editPlayerWeight(id, weight)
            playerRepository.editPlayerPerWeek(id,times_per_week)
            playerRepository.editPlayerFitness(id,fitness)

        }
    }

    suspend fun add(player: Player){
        playerRepository.add(player)
    }
}