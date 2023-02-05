package com.example.thisappwillbefunny.presentation.fr.get_random_cat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText

@Composable
fun GetRandomCatFragment() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val test =createRef()

        LargeText(value = "Get Random Cat", modifier = Modifier.constrainAs(test) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        })
    }
}