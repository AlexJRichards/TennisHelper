package com.example.tenhelper.ui.activity

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
class ViewActivitiesFragment : Fragment() {
    // View activities from database
    val viewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {

                    }
                ) {
                    Column(
                    ) {
                        Row() {
                            val allActivity = viewModel.activity.value
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
                                        text = "Completed Activity List",
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
                                Button(
                                    onClick = {
                                        findNavController().navigate(R.id.action_viewActivitiesFragment_to_addActivityFragment)
                                    }) {
                                    Text(text = "Add Activity")
                                }
                            }

                                LazyColumn() {

                                    allActivity.forEach { (id, date, distance) ->
                                        item {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .border(BorderStroke(1.dp, Color.DarkGray)),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween

                                            ) {
                                                Column() {
                                                    Text(text = "Date Completed: ${date}")
                                                    Text(text = "Distance Completed : ${distance} KM")
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