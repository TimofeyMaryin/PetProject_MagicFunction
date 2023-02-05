package com.example.thisappwillbefunny.presentation.fr.select_activity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText

@Composable
fun SelectActivityFragment() {
    var content by remember { mutableStateOf<ActivityPOJO?>(null) }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val test = createRef()


        if (content != null) {
            LargeText(value = content!!.activity, modifier = Modifier.constrainAs(test) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            })

        }


    }


    LaunchedEffect(key1 = Unit, block = {
        content = GetCurrentActivity.execute()
    })
}