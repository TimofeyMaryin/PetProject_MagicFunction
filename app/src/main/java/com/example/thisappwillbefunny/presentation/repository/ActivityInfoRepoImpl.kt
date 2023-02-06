package com.example.thisappwillbefunny.presentation.repository

import androidx.core.util.rangeTo
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.ActivityModel
import com.example.thisappwillbefunny.domain.repository.ActivityInfoRepository

class ActivityInfoRepoImpl: ActivityInfoRepository {
    override fun getAccessibility(value: Float): ActivityModel {
        return when(value) {
            in 0f..0.3f -> ActivityModel("you can definitely do it", R.drawable.reload)
            in 0.30001f..0.5f -> ActivityModel("have to try hard to get it done", R.drawable.reload)
            in 0.50005f..0.7f -> ActivityModel("You have to be an expert to do it.", R.drawable.reload)
            in 0.70001f..1f -> ActivityModel("I will suck you if you do it", R.drawable.reload)
            else -> ActivityModel("What the fuck... Sometimes was wrong", R.drawable.reload)
        }
    }

    override fun getParticipants(value: Int): ActivityModel {
        return when(value) {
            in 1..3 -> ActivityModel("Well, the benefit of a lot of people and do not need ($value)", R.drawable.reload)
            in 4..7 -> ActivityModel("get ready to gather all your friends ($value)", R.drawable.reload)
            in 8..12 -> ActivityModel("Invite everyone you see to complete this task. ($value)", R.drawable.reload)
            else -> ActivityModel("Gotta have a mass gathering for this fun ($value)", R.drawable.reload)
        }
    }

    override fun getPrice(value: Float): ActivityModel {
        return when(value) {
            0f -> ActivityModel("It's free, don't worry", R.drawable.reload)
            in 0.0001f..0.3f -> ActivityModel("Have to pay a little", R.drawable.reload)
            in 0.3001f..0.5f -> ActivityModel("You can ask your mom for money", R.drawable.reload)
            in 0.5001f..0.7f -> ActivityModel("First you have to work to afford it.", R.drawable.reload)
            in 0.7001f..1f -> ActivityModel("If you're not a millionaire, then you better skip it", R.drawable.reload)
            else -> ActivityModel("What the fuck... Sometimes was wrong", R.drawable.reload)
        }
    }

}