package com.example.tenhelper.ui.fitness

import com.example.tenhelper.ui.player.PlayerViewModel

class PlanGenerator(
) {
    fun changeLengths(viewModel: PlayerViewModel,
                      fitnessViewModel: FitnessViewModel){
        var players = viewModel.players.value
        println(players[0])
        val player = players[0]
        var fitnessLevel : Int

        var tennis : Int = 0

        var weight = player.weight
        var height = player.height.toDouble()
        var times = player.times_per_week
        var level = player.tennis_rating
        var bmiCatagory : Int = 0

        level = 11 - level

        if (level >= 5){
            if (times >= 4){
                tennis = 8
            }
            else {
                tennis = 6
            }
        }

        if (level <= 6){
            if (times >= 4){
                tennis = 6
            } else {
                tennis = 4
            }
        }
        //weight in kilograms divided by height in meters squared
        height = height / 100.00 // convert cm to m
        var bmi = (weight.toDouble() / (height.toFloat() * height.toFloat()))

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


        fitnessLevel = tennis + bmiCatagory / 2
        println("Values of the player")
        println(bmi)
        println(weight)
        println(height)
        println(fitnessLevel)
        println(tennis)


        var plans = fitnessViewModel.plans

        for (plan in plans.value){
            if (fitnessLevel >= 8){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 1.5)
            } else if (fitnessLevel <=7 && fitnessLevel >= 6){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 1)
            } else if (fitnessLevel <= 4){
                fitnessViewModel.updateLength(plan.id, plan.activityLength.toDouble() * 0.5)
            }

        }

//            below 18.5 – you're in the underweight range
//            between 18.5 and 24.9 – you're in the healthy weight range
//            between 25 and 29.9 – you're in the overweight range
//            between 30 and 39.9 – you're in the obese range
//              https://www.nhs.uk/common-health-questions/lifestyle/what-is-the-body-mass-index-bmi/

    }

    // Take Current Fitness Level to Choose how hard workouts be

    // Take in when player wants to peak for

    // Base fitness plan off of ITF one

    // Add to calendar?

    // Record activity option?

}