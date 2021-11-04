package com.example.tenhelper

import android.R.id
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.content.Intent
import android.view.View
import android.widget.Button
import android.R.id.button1
import android.view.MenuItem
import com.example.tenhelper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //Set Title of app
        val actionBar = getSupportActionBar()
        actionBar?.title = "Tennis App"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun onClick(v: View?) {
        // onclick method to put intent to start tracker activity class when called
        // adapted from github post by Rahul
        // accessed Oct 2021
        // https://stackoverflow.com/questions/45518139/kotlin-android-start-new-activity
        val i = Intent(applicationContext, TrackerActivity::class.java)
        startActivity(i)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Code to return when back button on top toolbar is clicked
        // Taken from https://www.py4u.net/discuss/604058
        // Accessed 10/10/21
        when (item.itemId) {
            id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}