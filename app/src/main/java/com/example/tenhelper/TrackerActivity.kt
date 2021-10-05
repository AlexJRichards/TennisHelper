package com.example.tenhelper

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.tenhelper.data.Player
import com.example.tenhelper.databinding.ActivityMainBinding
import com.example.tenhelper.databinding.TrackerFragmentBinding
import com.example.tenhelper.ui.player.PlayerViewModel
import kotlinx.coroutines.launch

class TrackerActivity : AppCompatActivity(), SensorEventListener {

    private var sManager: SensorManager? = null
    private var steps = 0
    val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var binding: TrackerFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TrackerFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        resetSteps()
        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    override fun onSensorChanged(event: SensorEvent?) {
        val sensor = event!!.sensor
        if (sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            steps++
            binding.activityTaken.text = steps.toString()
            binding.distanceTaken.text = getDistanceRun(steps).toString()
        }
    }

    fun resetSteps(){
        binding.reset.setOnClickListener {
            steps = 0
            binding.activityTaken.text = "0"
        }

    }

    private fun saveData(){

    }

    private fun loadData(){
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onResume(){
        super.onResume()
        val stepSensor: Sensor? = sManager?.getDefaultSensor((Sensor.TYPE_STEP_DETECTOR))
        if (stepSensor == null){
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }

    }

    //function to determine the distance run in kilometers using average step length for men or women
    fun getDistanceRun(steps: Int): Double {
        val players = playerViewModel.players.value
        if (players[0].gender == "M"){
            return (2.5 * steps) / 5280
        } else {
            return (2.2 * steps) / 5280
        }
    }

}