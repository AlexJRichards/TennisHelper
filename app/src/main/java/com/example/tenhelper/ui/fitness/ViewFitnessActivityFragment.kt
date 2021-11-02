package com.example.tenhelper.ui.fitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tenhelper.R
import com.example.tenhelper.ui.activity.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ViewFitnessActivityFragment : Fragment() {
    val fitnessViewModel: FitnessViewModel by viewModels()
    val activityViewModel: ActivityViewModel by viewModels()
    val args: ViewFitnessActivityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aID = args.fitnessId
        return ComposeView(requireContext()).apply {
            setContent {
                val plan = fitnessViewModel.plans.value
                val id = aID?.toInt()
                Scaffold(
                ) {
                    Column() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "View Activity",
                                style = MaterialTheme.typography.h3,
                                textAlign = TextAlign.Center
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
                                .padding(16.dp)
                                .border(BorderStroke(2.dp, Color.Black))
                        ) {
                            plan.forEach { (ID, week, day, activityName, activityType, activityLength, activitySets, activityDescription, completed) ->
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(BorderStroke(1.dp, Color.DarkGray)),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween

                                    ) {
                                        Column() {
                                            if (ID == id) {
                                                Text(text = "Week ${week}, Day ${day}")
                                                Text(text = "Activity Name: ${activityName}")
                                                Text(text = "Type of activity: ${activityType}")
                                                Text(text = "Length: ${activityLength.toString()} minutes")
                                                Text(text = "Sets: ${activitySets}")
                                                Text(text = "Description : ${activityDescription}")
                                                if (!completed!!) {
                                                    Text(text = "Activity not completed")
                                                    Button(onClick = {
                                                        fitnessViewModel.markComplete(id)
//                                                        val action = ViewFitnessActivityFragmentDirections.actionViewFitnessActivityFragmentSelf2(
//                                                            aID
//                                                        )
                                                        findNavController().navigate(R.id.action_viewFitnessActivityFragment_to_fitnessFragment)
                                                    }) {
                                                        Text(text = "Mark Completed")
                                                    }
                                                } else {
                                                    Text(text = "Activity was completed")
                                                    Button(onClick = {
                                                        fitnessViewModel.markIncomplete(id)
//                                                        val action = ViewFitnessActivityFragmentDirections.actionViewFitnessActivityFragmentSelf2(
//                                                            aID
//                                                        )
                                                        findNavController().navigate(R.id.action_viewFitnessActivityFragment_to_fitnessFragment)
                                                    }) {
                                                        Text(text = "Mark Incomplete")
                                                    }
                                                }
                                                Button(
                                                    onClick = {
                                                        findNavController().navigate(R.id.action_viewFitnessActivityFragment_to_trackerActivity)
                                                    }) {
                                                    Text(text = "Track Activity")
                                                }
                                                Text(text = "Distance Completed? (If LSD/Cardio)")
                                                var distance by remember { mutableStateOf("") }
                                                TextField(
                                                    value = distance,
                                                    onValueChange = { distance = it },
                                                    label = { Text("Distance") }
                                                )
                                                val date = Calendar.getInstance().time
                                                val sdf = SimpleDateFormat("dd.MM.yyyy")
                                                val formatedDate = sdf.format(date)
                                                Button(

                                                    onClick = {
                                                        activityViewModel.addActivity(0,formatedDate,distance.toFloat())
                                                        findNavController().navigate(R.id.fitnessFragment)
                                                    }) {
                                                    Text(text = "Submit")
                                                }
                                            }


                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}