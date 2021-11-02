package com.example.tenhelper.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.tenhelper.R
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ViewGoalsFragment : Fragment() {
    val viewModel: GoalsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                ) {
                    Column(
                    ) {
                        Row() {

                            // get list of goals
                            val allGoals = viewModel.goals.value
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
                                        text = "Goals",
                                        style = MaterialTheme.typography.h3,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color.White)
                                        .padding(8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            findNavController().navigate(R.id.action_viewGoalsFragment_to_goalsFragment)
                                        }) {
                                        Text(text = "Add Goal")
                                    }
                                }


                                LazyColumn() {

                                    allGoals.forEach { (goalNum, goalName, goalDescription, goalDate, goalDate2, completed) ->
                                        item {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .border(BorderStroke(1.dp, Color.DarkGray)),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween

                                            ) {
                                                Column() {
                                                    Text(text = "Goal Number: ${goalNum}")
                                                    Text(text = "Goal Name: ${goalName}")
                                                    Text(text = "Goal Description: ${goalDescription}")
                                                    Text(text = "Completion Date: ${goalDate2}")
                                                    if (completed) {
                                                        Text(text = "Status: Completed")
                                                        Button(
                                                            onClick = {
                                                                viewModel.markGoalUnComplete(goalNum)
                                                                findNavController().navigate(R.id.viewGoalsFragment)
                                                            }) {
                                                            Text(text = "Mark Incomplete")
                                                        }
                                                    } else {
                                                        Text(text = "Status: Incomplete")
                                                        Button(
                                                            onClick = {
                                                                viewModel.markGoalCompleted(goalNum)
                                                                findNavController().navigate(R.id.viewGoalsFragment)
                                                            }) {
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
        }
    }
}