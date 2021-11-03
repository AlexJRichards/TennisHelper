package com.example.tenhelper.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.findNavController
import com.example.tenhelper.R
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddActivityFragment : Fragment() {
    // Fragment to add activity
    val viewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                    text = "Enter Distance Completed",
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        item() {
                            var distance by remember { mutableStateOf("") }
                            TextField(
                                value = distance,
                                onValueChange = { distance = it },
                                label = { Text("Distance Completed") },
                            )
                            // date format code adapted from guide
                            // https://www.datetimeformatter.com/how-to-format-date-time-in-kotlin/
                            // accessed 2/10/21
                            val date = Calendar.getInstance().time
                            val sdf = SimpleDateFormat("dd.MM.yyyy")
                            val formatedDate = sdf.format(date)
                            Button(
                                onClick = {
                                    viewModel.addActivity(0,formatedDate,distance.toFloat())
                                    findNavController().navigate(R.id.action_addActivityFragment_to_viewActivitiesFragment)
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

