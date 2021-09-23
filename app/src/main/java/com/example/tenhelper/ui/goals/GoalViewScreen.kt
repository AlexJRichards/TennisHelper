package com.example.tenhelper.ui.goals


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewGoalsScreen(
    viewModel: GoalsViewModel
) {
    Scaffold(
        topBar = {

        }
    ) {
        Column(
        ) {
            Row() {
                val allGoals = viewModel.goals.value
                val completedGoals = viewModel.goals.value
//                Text(text = "Completed Goals")
//                LazyColumn() {
//                    completedGoals.forEach { (goalNum, goalName, goalDescription, goalDate, goalDate2, completed) ->
//                        item {
//                            Text(text = goalNum.toString())
//                            Text(text = goalName)
//                            Text(text = goalDescription)
//                            Text(text = goalDate2)
//                            if(completed){
//                                Text(text = "Completed")
//                            } else {
//                                Text(text = "Uncomplete")
//                            }
//                            Button(
//                                onClick = {
//                                    viewModel.markGoalUnComplete(goalNum)
//                                }) {
//                                Text(text = "Mark Not Completed")
//                            }
//                        }
//
//                    }
//                }
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Goals",
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center)
                    }
                    LazyColumn() {

                        allGoals.forEach { (goalNum, goalName, goalDescription, goalDate, goalDate2, completed) ->
                            item {
                                Text(text = goalNum.toString())
                                Text(text = goalName)
                                Text(text = goalDescription)
                                Text(text = goalDate2)
                                if(completed){
                                    Text(text = "Completed")
                                    Button(
                                        onClick = {
                                            viewModel.markGoalUnComplete(goalNum)
                                        }) {
                                        Text(text = "Mark Incomplete")
                                    }
                                } else {
                                    Text(text = "Incomplete")
                                    Button(
                                        onClick = {
                                            viewModel.markGoalCompleted(goalNum)
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