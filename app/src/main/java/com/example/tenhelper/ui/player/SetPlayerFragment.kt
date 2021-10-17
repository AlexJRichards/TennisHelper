package com.example.tenhelper.ui.player

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tenhelper.R
import com.example.tenhelper.ui.fitness.FitnessViewModel
import com.example.tenhelper.ui.goals.GoalsViewModel
import com.example.tenhelper.ui.goals.ViewGoalsScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SetPlayerFragment : Fragment() {

    val fitnessViewModel: FitnessViewModel by viewModels()
    val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                Scaffold(

                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.White)
                    ) {
                        item() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.White)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Enter the following : Name, BMI, Tennis Level(LTA Rating).\n " +
                                            "Fitness Level is calculated by bleep test.",
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item(){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.White)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "In Height/Weight/TennisLevel/Times/Fitness fields, please type only a number",
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }

                        item() {
                            var name by remember { mutableStateOf("") }
                            TextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Name") },
                            )
                            var gender by remember { mutableStateOf("") }
                            TextField(
                                value = gender,
                                onValueChange = { gender = it },
                                label = { Text("Gender (Type 'M' / 'F' or 'NB')") },
                            )
                            var height by remember { mutableStateOf("") }
                            TextField(
                                value = height,
                                onValueChange = { height = it },
                                label = { Text("Height (CM) as only number") }
                            )
                            var weight by remember { mutableStateOf("") }
                            TextField(
                                value = weight,
                                onValueChange = { weight = it },
                                label = { Text("Weight (KG) as only number") }
                            )
                            var tennisLevel by remember { mutableStateOf("") }
                            TextField(
                                value = tennisLevel,
                                onValueChange = { tennisLevel = it },
                                label = { Text("Tennis Level (LTA Rating)") },
                            )
                            var timesPerWeek by remember { mutableStateOf("") }
                            TextField(
                                value = timesPerWeek,
                                onValueChange = { timesPerWeek = it },
                                label = { Text("How many times do you play tennis a week?") }
                            )
                            var fitness by remember { mutableStateOf("") }
                            TextField(
                                value = fitness,
                                onValueChange = { fitness = it },
                                label = { Text("Fitness Test Score (Beep Test Score)") }
                            )

                            Button(
                                onClick = {
                                    val fitScore = calcFitness(fitness.toInt(), gender)
                                    playerViewModel.addPlayer(
                                        0,
                                        name,
                                        height.toInt(),
                                        weight.toInt(),
                                        calcTennisLevel(tennisLevel.toDouble()).toDouble(),
                                        fitScore,
                                        gender,
                                        timesPerWeek.toInt()
                                    )
                                    findNavController().navigate(R.id.action_setPlayerFragment_to_navigation_home)
                                }) {
                                Text(text = "Submit")
                            }
                        }
                    }

                }
            }
        }
    }

    private fun calcFitness(Score:Int, Gender:String) : Int{
        if (Gender == "M"){
            if (Score >= 11){
                return 10
            } else if (Score >= 9 && Score < 11){
                return 8
            } else if (Score >= 7 && Score < 9){
                return 6
            } else if (Score <= 6){
                return 3
            }
        } else {
            if (Score >= 10){
                return 10
            } else if (Score >= 8 && Score < 10){
                return 8
            } else if (Score >= 6 && Score < 8){
                return 6
            } else if (Score <= 5){
                return 3
            }
        }
        return 5
    }

    private fun calcTennisLevel(Rating:Double) : Int{
        val rate = Rating.toInt()
        return rate
    }
}
