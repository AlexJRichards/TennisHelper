package com.example.tenhelper.ui.fitness

import androidx.lifecycle.ViewModelProvider
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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.tenhelper.R
import com.example.tenhelper.ui.dashboard.DashboardFragmentDirections
import com.example.tenhelper.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FitnessFragment : Fragment() {

    companion object {
        fun newInstance() = FitnessFragment()
    }

    val playerViewModel: PlayerViewModel by viewModels()
    val viewModel: FitnessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val plan = viewModel.plans.value
        return ComposeView(requireContext()).apply {
            setContent {
                Column() {
                    val plan = viewModel.plans.value
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "View Plan",
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .border(BorderStroke(2.dp, Color.Black))
                    ) {
                        plan.forEach { (id, week, day, activityName, activityType, activityLength, activitySets, activityDescription, completed) ->
                            item {
                                if (week == 1){
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(BorderStroke(1.dp, Color.DarkGray))
                                        ,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween

                                    ) {
                                        Column() {
                                            Text(text = "Week ${week}, Day ${day}")
                                            Text(text = "Activity Name: ${activityName}")
                                            Text(text = "Type of activity: ${activityType}")
                                            Text(text = "Length: ${activityLength.toString()} minutes")
                                            Text(text = "Sets: ${activitySets}")
                                            Text(text = "Description : ${activityDescription}")
                                            Button(onClick = {
                                                val action = FitnessFragmentDirections.actionFitnessFragmentToViewFitnessActivityFragment(
                                                    id.toString()
                                                )
                                                findNavController().navigate(action)
                                            }) {
                                                Text(text = "View Activity/Mark Completed")
                                            }
//                                    if (!completed!!){
//                                        Text(text = "Activity not completed")
//                                        Button(onClick = { viewModel.markComplete(id) }) {
//                                            Text(text = "Mark Completed")
//                                        }
//
//                                    } else {
//                                        Text(text = "Activity was completed")
//                                        Button(onClick = { viewModel.markIncomplete(id) }) {
//                                            Text(text = "Mark Incomplete")
//                                        }
//                                    }

                                        }

                                    }
                                }
                                Spacer(modifier = Modifier.padding(2.dp))
                                if (week == 2){
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(BorderStroke(1.dp, Color.DarkGray)),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween

                                    ) {
                                        Column() {
                                            Text(text = "Week ${week}, Day ${day}")
                                            Text(text = "Activity Name: ${activityName}")
                                            Text(text = "Type of activity: ${activityType}")
                                            Text(text = "Length: ${activityLength.toString()} minutes")
                                            Text(text = "Sets: ${activitySets}")
                                            Text(text = "Description : ${activityDescription}")
                                            Button(onClick = { viewModel.markComplete(id) }) {
                                                Text(text = "Mark Completed")
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