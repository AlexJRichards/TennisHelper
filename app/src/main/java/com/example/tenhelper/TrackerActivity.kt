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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tenhelper.databinding.ActivityMainBinding
import com.example.tenhelper.databinding.TrackerFragmentBinding

class TrackerActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    private var totalSteps = 0
    private var running = false
    private var prevTotalSteps = 0

    private lateinit var binding: TrackerFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding = TrackerFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        loadData()
        resetSteps()
        binding.backToHome.setOnClickListener {
            findNavController().navigate(R.id.home)
        }
    }


    override fun onSensorChanged(event: SensorEvent?) {

        if (running){
            if (event != null) {
                totalSteps = event.values[0].toInt()
                val curSteps:Int = totalSteps.toInt() - prevTotalSteps.toInt()
                binding.activityTaken.text = "${curSteps}"
            }

        }
    }

    fun resetSteps(){
        binding.activityTaken.setOnClickListener{
            prevTotalSteps = totalSteps
            binding.activityTaken.text = "0"
            saveData()
            true
        }
    }

    private fun saveData(){
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("key1", prevTotalSteps)
        editor.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getInt("key1", 0)
        prevTotalSteps = savedNumber
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onResume(){
        super.onResume()
        binding.start.setOnClickListener(){
            running = true
        }

        binding.stop.setOnClickListener {

        }

        val stepSensor = sensorManager?.getDefaultSensor((Sensor.TYPE_STEP_COUNTER))
        println("In tracker activity")

        sensorManager?.registerListener(this,stepSensor, SensorManager.SENSOR_DELAY_UI)
    }

    //function to determine the distance run in kilometers using average step length for men and number of steps
    fun getDistanceRun(steps: Long): Float {
        return (steps * 78).toFloat() / 100000.toFloat()
    }

}