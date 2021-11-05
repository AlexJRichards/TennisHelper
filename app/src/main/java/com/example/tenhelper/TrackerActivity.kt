package com.example.tenhelper

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tenhelper.databinding.TrackerFragmentBinding
import com.example.tenhelper.ui.player.PlayerViewModel

class TrackerActivity : AppCompatActivity(), SensorEventListener {

    // adapted from android track steps java tutorial by lewis gavin
    // accessed 28/09/2021
    // https://www.lewisgavin.co.uk/Step-Tracker-Android/

    private var sensorManager: SensorManager? = null
    private var steps = 0
    val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var binding: TrackerFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TrackerFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        resetSteps()
        // initialise sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    override fun onSensorChanged(event: SensorEvent?) {
        // detect event and add to steps when step taken
        // update text on steps and distance strings
        val sensor = event!!.sensor
        if (sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            steps++
            binding.activityTaken.text = steps.toString()
            binding.distanceTaken.text = getDistanceRun(steps).toString()
        }
    }

    fun resetSteps(){
        // set click listener for reset button to reset text to 0
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
        // Code to register sensor and check if tracker compatible with phone
        // Code taken from youtube tutorial code palace
        // accessed 01/10/2021
        // https://www.youtube.com/watch?v=WSx2a99kPY4&ab_channel=CodePalace
        val stepSensor: Sensor? = sensorManager?.getDefaultSensor((Sensor.TYPE_STEP_DETECTOR))
        if (stepSensor == null){
            Toast.makeText(this, "Tracker not compatible with phone - no step sensor detected", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    //function to determine the distance run in kilometers using average step length for men or women
    fun getDistanceRun(steps: Int): Double {
        // Code to calculate distance in activity
        // adapted from java tutorial by lewis gavin
        // accessed 28/09/2021
        // https://www.lewisgavin.co.uk/Step-Tracker-Android/
        val players = playerViewModel.players.value
        // Return * average step length for male or not
        if (players[0].gender == "M"){
            return (2.5 * steps) / (5280 * 1.6)
        } else {
            return (2.2 * steps) / (5280 * 1.6)
        }
    }

}