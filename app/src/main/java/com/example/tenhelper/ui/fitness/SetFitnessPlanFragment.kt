package com.example.tenhelper.ui.fitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
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
import com.example.tenhelper.R
import com.example.tenhelper.ui.dashboard.DashboardFragmentDirections
import com.example.tenhelper.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

// Class for using plangenerator for user to set plan

@AndroidEntryPoint
class SetFitnessPlanFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: FitnessViewModel by viewModels()
        val playerViewModel: PlayerViewModel by viewModels()

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
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Generate Plan",
                                style = MaterialTheme.typography.h3,
                                textAlign = TextAlign.Center)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                                .height(60.dp)
                        ) {
                            var players = playerViewModel.players.value
                            // Display name of user and option to generate fitness plan
                            // Function is adaptable in future if more than one player is using the application
                            LazyColumn() {
                                players.forEach { (id, name, height, weight, rating, timesPerWeek) ->
                                    item {
                                        Text(text = "${name}")
                                        val generator = PlanGenerator()
                                        Button(onClick = {
                                            generator.changeLengths(playerViewModel, viewModel)
                                            val action = SetFitnessPlanFragmentDirections.actionSetFitnessPlanFragmentToFitnessFragment(name)
                                            findNavController().navigate(action)
                                        }) {
                                            Text(text = "Generate ${name}'s plan")
                                        }
                                    }
                                }
                            }
                        }

                    }
            }
        }
}
    }}