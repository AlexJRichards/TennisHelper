package com.example.tenhelper.ui.goals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.tenhelper.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class GoalsFragment : Fragment() {
    // Add goals fragment
    val viewModel: GoalsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Goals Should be SMART(Specific, Measurable, Achievable, Realistic and Time-Related), enter the goal in the description and the date in the following format : DD/MM/YY",
                                style = MaterialTheme.typography.body1,
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
                            Text(
                                text = "Set Goal",
                                style = MaterialTheme.typography.h3,
                                textAlign = TextAlign.Center
                            )
                        }
                        // Code adapted/learnt from documentation
                        // https://developer.android.com/jetpack/compose/text
                        // Accessed various dates from July 2021
                        var goalName by remember { mutableStateOf("") }
                        TextField(
                            value = goalName,
                            onValueChange = { goalName = it },
                            label = { Text("Goal Name") }
                        )
                        var goalDesc by remember { mutableStateOf("") }
                        TextField(
                            value = goalDesc,
                            onValueChange = { goalDesc = it },
                            label = { Text("Goal Description") }
                        )
                        var dateBy by remember { mutableStateOf("") }
                        TextField(
                            value = dateBy,
                            onValueChange = { dateBy = it },
                            label = { Text("Date By") }
                        )
                        val dateToday: Date = Calendar.getInstance().getTime()
                        Button(
                            onClick = {
                                viewModel.addGoal(goalName, goalDesc, dateToday.toString(), dateBy)
                                // get new values for goals to display all goals when redirected
                                viewModel.viewModelScope.launch {
                                    viewModel.goals.value = viewModel.getAllGoals()
                                }
                                findNavController().navigate(R.id.action_goalsFragment_to_viewGoalsFragment)
                            }) {
                            Text(text = "Submit")
                        }
                    }

                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}