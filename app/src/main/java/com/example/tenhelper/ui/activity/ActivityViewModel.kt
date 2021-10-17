package com.example.tenhelper.ui.activity


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tenhelper.data.Activity
import com.example.tenhelper.data.FitnessPlan
import com.example.tenhelper.data.repositories.ActivityRepository
import com.example.tenhelper.data.repositories.FitnessRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val activityRepository: ActivityRepository) : ViewModel(){
    var loading: Boolean = true

    val activity: MutableState<List<Activity>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            activity.value = activityRepository.getAll()
            loading = false
        }
    }

    fun addActivity(id:Int, date:String, distance:Float){
        viewModelScope.launch {
            val a = Activity(id=0, date, distance)
            activityRepository.add(a)
        }
    }

    suspend fun getAll() : List<Activity> {
        return activityRepository.getAll()
    }

}