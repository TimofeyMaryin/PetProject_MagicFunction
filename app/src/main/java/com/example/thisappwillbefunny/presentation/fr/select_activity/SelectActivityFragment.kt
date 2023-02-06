package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(test) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                LargeText(value = content!!.activity)
                LargeText(value = content!!.key)
                LargeText(value = content!!.link)
                Log.e("SelectActivityFragment",  content!!.link, )
                LargeText(value = content!!.type)
                LargeText(value = content!!.accessibility.toString())
                LargeText(value = content!!.participants.toString())
                LargeText(value = content!!.price.toString())

            }

        }


    }


    LaunchedEffect(key1 = Unit, block = {
        content = GetCurrentActivity.execute()
    })
}