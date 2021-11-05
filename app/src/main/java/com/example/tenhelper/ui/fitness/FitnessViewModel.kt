package com.example.tenhelper.ui.fitness


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tenhelper.data.FitnessPlan
import com.example.tenhelper.data.repositories.FitnessRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FitnessViewModel @Inject constructor(private val fitnessRepository: FitnessRepository) : ViewModel(){
    val plans: MutableState<List<FitnessPlan>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            plans.value = fitnessRepository.getAll()
        }
    }

    suspend fun getAll() : List<FitnessPlan> {
        return fitnessRepository.getAll()
    }

    fun markComplete(id:Int){
        viewModelScope.launch {
            fitnessRepository.markCompleted(id)
        }
    }

    fun markIncomplete(id:Int){
        viewModelScope.launch {
            fitnessRepository.markIncompleted(id)
        }
    }

    fun updateLength(id:Int, howMuch:Double){
        viewModelScope.launch {
            fitnessRepository.adjustLength(howMuch, id)
        }
    }

    fun addDistance(id:Int, distance:Int){
        viewModelScope.launch {
            fitnessRepository.editDistance(id, distance)
        }
    }
}