package com.example.tenhelper.ui.fitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.navigation.findNavController
import com.example.tenhelper.R
import com.example.tenhelper.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

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
                            var playersNames = mutableListOf<String>()
                            for (p in playerViewModel.players.value){
                                playersNames.add(p.player_name)
                            }
                            println(playersNames)

                            var expanded by remember { mutableStateOf(false) }
                            var selected by remember { mutableStateOf(playersNames.get(0)) }
                            val disabledValue = "B"

                            Box(modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.TopStart)) {
                                Button(onClick = { expanded = true }) {
                                    Text(
                                        text = "Select Player",
                                        style = MaterialTheme.typography.h4,
                                        textAlign = TextAlign.Left
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    playersNames.forEachIndexed { index, s ->
                                        DropdownMenuItem(onClick = {
                                            selected = index.toString()
                                            expanded = false
                                        }) {
                                            val disabledText = if (s == disabledValue) {
                                                " (Disabled)"
                                            } else {
                                                ""
                                            }
                                            Text(text = s + disabledText)
                                        }
                                    }
                                }
                            }

                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(8.dp)){
                            Box() {
                                val generator = PlanGenerator()
                                Button(
                                    onClick = {
                                        generator.changeLengths(playerViewModel, viewModel)
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
    }}