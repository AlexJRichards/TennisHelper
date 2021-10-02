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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tenhelper.databinding.ActivityMainBinding
import com.example.tenhelper.databinding.TrackerFragmentBinding

class TrackerActivity : AppCompatActivity(), SensorEventListener {

    private var sManager: SensorManager? = null
    private var steps = 0

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

    //function to determine the distance run in kilometers using average step length for men and number of steps
    fun getDistanceRun(steps: Long): Float {
        return (steps * 78).toFloat() / 100000.toFloat()
    }

}