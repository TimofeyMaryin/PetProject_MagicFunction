package com.example.thisappwillbefunny.domain.model

import androidx.compose.runtime.MutableState


/**
 * Модель, которая исопльзуется в SelectActivityFragment.
 */

data class FilterElementModel(
    val icon: Int,
    var isActive: MutableState<Boolean>,

)
