package com.example.tenhelper.ui.goals

import androidx.lifecycle.ViewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.tenhelper.data.Goal
import com.example.tenhelper.data.repositories.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val goalRepository: GoalRepository) : ViewModel(){
    var loading: Boolean = true

    //state: notes list
    val goals: MutableState<List<Goal>> = mutableStateOf(listOf())
    val completedGoals: MutableState<List<Goal>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            goals.value = goalRepository.getAllGoals()
            completedGoals.value = goalRepository.getCompletedGoals()
            loading = false
        }
    }

    fun addGoal(
        goal_name: String,
        goal_description: String,
        goal_start:String,
        goal_end:String
    ){
        viewModelScope.launch {
            goalRepository.addGoal(Goal(goal_name = goal_name,goal_description = goal_description, goal_start = goal_start, goal_end = goal_end, goal_completed = false))
        }
    }

    suspend fun getAllGoals() : List<Goal>{
        return goalRepository.getAllGoals()
    }

    fun markGoalCompleted(goalID:Int){
        viewModelScope.launch {
            goalRepository.markCompleted(goalID)
        }
    }

    fun markGoalUnComplete(goalID:Int){
        viewModelScope.launch {
            goalRepository.markUncompleted(goalID)
        }
    }

}