package com.example.thisappwillbefunny.domain.repository

import com.example.thisappwillbefunny.domain.model.ActivityModel


interface ActivityInfoRepository {

    fun getAccessibility(value: Float): ActivityModel
    fun getParticipants(value: Int): ActivityModel
    fun getPrice(value: Float): ActivityModel

}