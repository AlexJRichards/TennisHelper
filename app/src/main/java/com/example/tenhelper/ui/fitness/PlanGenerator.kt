package com.example.tenhelper.ui.fitness

import com.example.tenhelper.ui.player.PlayerViewModel

// Class to generate fitness plan by scoring user and using score to edit pre existing plan
class PlanGenerator(
) {
    fun changeLengths(viewModel: PlayerViewModel,
                      fitnessViewModel: FitnessViewModel){
        var players = viewModel.players.value
        val player = players[0]
        var fitnessLevel : Int

        var tennis : Int = 0
        // import player statistics for calculations
        var weight = player.weight
        var height = player.height.toDouble()
        var times = player.times_per_week
        var level = player.tennis_rating
        var fitness = player.fitness
        var bmiCatagory : Int = 0


        // Convert tennis rating into 1-10 scale
        level = 11 - level

        //BMI Calculation
        height = height / 100.00 // convert cm to m
        var bmi = (weight.toDouble() / (height.toFloat() * height.toFloat()))

        // Rate tennis score for plan depending on rating/times per week played
        if (level > 6){
            if (times >= 4){
                tennis = 8
            }
            else {
                tennis = 6
            }
        } else {
            if (times >= 4){
                tennis = 6
            } else {
                tennis = 4
            }
        }
        // Score BMI on 1-10 scale,
        // using results from NHS
        // https://www.nhs.uk/common-health-questions/lifestyle/what-is-the-body-mass-index-bmi/
        //            below 18.5 – you're in the underweight range
        //            between 18.5 and 24.9 – you're in the healthy weight range
        //            between 25 and 29.9 – you're in the overweight range
        //            between 30 and 39.9 – you're in the obese range

        if (bmi < 18.5){
            bmiCatagory = 8
        }
        if (bmi >= 18.5 && bmi <= 24.9) {
            bmiCatagory = 5
        }
        if (bmi >= 25 && bmi <= 29.9) {
            bmiCatagory = 3
        }
        if (bmi >= 30) {
            bmiCatagory = 1
        }

        // Average three catagories into fitness level for plan
        fitnessLevel = tennis + bmiCatagory + fitness / 3

        // Import plan from fitness view model
        var plans = fitnessViewModel.plans

        // Adapt plan depending on fitness level value
        for (plan in plans.value){
            if (fitnessLevel >= 8){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 1.2)
            } else if (fitnessLevel <=7 && fitnessLevel >= 6){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 1)
            } else if (fitnessLevel <= 4){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 0.8)
            }

        }

    }
}