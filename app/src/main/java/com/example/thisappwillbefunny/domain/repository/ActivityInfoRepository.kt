package com.example.thisappwillbefunny.domain.repository

import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.model.ActivityModel


interface ActivityInfoRepository {

    fun getAccessibility(value: Float): ActivityItemDescModel
    fun getParticipants(value: Int): ActivityItemDescModel
    fun getPrice(value: Float): ActivityItemDescModel

}