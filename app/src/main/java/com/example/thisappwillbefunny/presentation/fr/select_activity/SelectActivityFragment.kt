package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText

@Composable
fun SelectActivityFragment(viewModel: SelectActivityViewModel) {
    var content by remember { mutableStateOf<ActivityPOJO?>(null) }

    if (content != null) {

        val animationSpec = remember { LottieAnimationSpec.RawRes(viewModel.getCurrentRawRes(content!!.type)) }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topBar) = createRefs()

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                LottieAnimation(spec = )
            }

        }
        Column(

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


    LaunchedEffect(key1 = Unit, block = {
        content = GetCurrentActivity.execute()
    })
}