package com.example.tenhelper.ui.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.tenhelper.R
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SetGoalScreen(
    viewModel: GoalsViewModel
) {
    Scaffold(
        topBar = {

        }
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
                    textAlign = TextAlign.Center)
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Set Goal",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center)
            }

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
            println(dateToday.toString() + " Date Today")
            Button(
                onClick = {
                    viewModel.addGoal(goalName, goalDesc, dateToday.toString(), dateBy)
                    viewModel.viewModelScope.launch {
                        viewModel.goals.value = viewModel.getAllGoals()
                    }
                }) {
                Text(text = "Submit")
            }
        }

    }
}
