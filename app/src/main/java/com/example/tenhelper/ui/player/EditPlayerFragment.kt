package com.example.tenhelper.ui.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tenhelper.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditPlayerFragment : Fragment() {

    private lateinit var playerName: String
    val args: EditPlayerFragmentArgs by navArgs()
    val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        playerName = args.playerName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
                                text = "Edit ${playerName}",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Center)
                        }


                        var players = playerViewModel.players
                        var id = 0
                        for (p in players){
                            if (p.player_name == playerName){
                                id = p.id
                            }
                        }

                        var name by remember { mutableStateOf("") }
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") }
                        )
                        var age by remember { mutableStateOf("") }
                        TextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text("Age") }
                        )
                        var height by remember { mutableStateOf("") }
                        TextField(
                            value = height,
                            onValueChange = { height = it },
                            label = { Text("Height") }
                        )
                        var weight by remember { mutableStateOf("") }
                        TextField(
                            value = weight,
                            onValueChange = { weight = it },
                            label = { Text("Weight") }
                        )
                        var tennisLevel by remember { mutableStateOf("") }
                        TextField(
                            value = tennisLevel,
                            onValueChange = { tennisLevel = it },
                            label = { Text("Tennis Level") }
                        )
                        var fitness by remember { mutableStateOf("") }
                        TextField(
                            value = fitness,
                            onValueChange = { fitness = it },
                            label = { Text("Fitness Level") }
                        )
                        var timesPerWeek by remember { mutableStateOf("") }
                        TextField(
                            value = timesPerWeek,
                            onValueChange = { timesPerWeek = it },
                            label = { Text("How many times do you play tennis a week?") }
                        )
                        val dateToday: Date = Calendar.getInstance().getTime()
                        Button(
                            onClick = {
//                    val p = Player(0, name, height.toInt(), weight.toInt(), tennisLevel.toDouble(), timesPerWeek.toInt())
                                playerViewModel.editPlayer(id, name, height.toInt(), weight.toInt(), tennisLevel.toDouble(), fitness.toInt(), timesPerWeek.toInt())
                                findNavController().navigate(R.id.home)
                            }) {
                            Text(text = "Submit")
                        }


                    }
                }
            }
        }
    }

}