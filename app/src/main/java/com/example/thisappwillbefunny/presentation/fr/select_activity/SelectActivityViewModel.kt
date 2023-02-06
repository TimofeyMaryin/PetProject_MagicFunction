package com.example.thisappwillbefunny.presentation.fr.select_activity

import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.repository.ActivityInfoRepoImpl

class SelectActivityViewModel : ViewModel() {


    fun getCurrentRawRes(type: String): Int =
        when(type){
            "education" -> R.raw.reading_boy
            "recreational" -> R.raw.relaxation
            "social" -> R.raw.social
            "diy" -> R.raw.diy
            "charity" -> R.raw.charity
            "cooking" -> R.raw.cooking
            "relaxation" -> R.raw.relax
            "music" -> R.raw.music
            "busywork" -> R.raw.busywork
            else -> -1
        }

    val activityRepo = ActivityInfoRepoImpl()
}