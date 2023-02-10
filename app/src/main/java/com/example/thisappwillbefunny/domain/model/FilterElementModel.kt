package com.example.thisappwillbefunny.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Brush


/**
 * Модель, которая исопльзуется в SelectActivityFragment.
 */

data class FilterElementModel(
    val icon: Int,
    var isActive: MutableState<Boolean>,
    val bg: Brush

)
