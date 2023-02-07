package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.domain.model.ActivityModel
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.utils.UiConst
import androidx.compose.ui.unit.dp
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.RequestActivityModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectActivityFragment(viewModel: SelectActivityViewModel) {
    var currentRequests by remember { mutableStateOf<List<RequestActivityModel>>(emptyList()) }
    var isLoad by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    val animationSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.diy))
    val progress by animateLottieCompositionAsState(
        animationSpec,
        iterations = Integer.MAX_VALUE
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LottieAnimation(
                composition = animationSpec,
                progress = progress,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .background(Color.Cyan)
                    .height(configuration.screenWidthDp.dp)
            )
        }
        items(viewModel.defaultCountRequest) {
            if (isLoad) {
                Box(modifier = Modifier.fillMaxWidth().padding(vertical = UiConst.Padding.SMALL), contentAlignment = Alignment.Center) {
                    ActivityItem(
                        activityName = currentRequests[it].activityPOJO.activity,
                        modifier = Modifier,
                        accessibilityModel = currentRequests[it].accessibleModel,
                        participantsModel = currentRequests[it].participants,
                        priceModel = currentRequests[it].pricing
                    )
                }
            }
        }



    }



    LaunchedEffect(key1 = Unit, block = {
        currentRequests = viewModel.requestActivity()
        Log.e("SelectActivityFragment", "currentRequest: ${currentRequests.size}", )
        if (currentRequests.size == viewModel.defaultCountRequest) {
            isLoad = true
        }
    })
}



