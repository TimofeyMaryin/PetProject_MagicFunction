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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.model.ActivityModel
import com.example.thisappwillbefunny.domain.repository.ActivityInfoRepository
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectActivityFragment(viewModel: SelectActivityViewModel) {
    var content by remember { mutableStateOf<ActivityPOJO?>(null) }


    if (content != null) {

        val animationSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(viewModel.getCurrentRawRes(content!!.type)))
        val progress by animateLottieCompositionAsState(
            animationSpec,
            iterations = Integer.MAX_VALUE
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {  }
            item {
                LottieAnimation(
                    composition = animationSpec,
                    progress = progress,
                    alignment = Alignment.TopCenter,
                )
            }

            item {
                Container() {
                    LargeText(
                        value = content!!.activity,
                        fontFamily = Raleway,
                    )
                }
            }

            item {
                AllInfoAboutActivity(viewModel = viewModel, content = content!!)
            }
        }
    }


    LaunchedEffect(key1 = Unit, block = {
        content = GetCurrentActivity.execute()
    })
}


@Composable
private fun AllInfoAboutActivity(
    viewModel: SelectActivityViewModel,
    content: ActivityPOJO
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AllInfoAboutActivityItem(model = viewModel.activityRepo.getAccessibility(content.accessibility.toFloat()), desc = "Accessibility")
        AllInfoAboutActivityItem(model = viewModel.activityRepo.getParticipants(content.participants), desc = "Participants")
        AllInfoAboutActivityItem(model = viewModel.activityRepo.getPrice(content.price.toFloat()), desc = "Price")
    }
}


@Composable
private fun AllInfoAboutActivityItem(
    model: ActivityModel,
    desc: String
) {
    Container() {
        Row(
            modifier = Modifier
                .clip(UiConst.Round.SMALL)
                .fillMaxWidth()
                .background(MaterialTheme.colors.onSecondary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Container(
                modifier = Modifier.weight(3f)
            ) {
                LargeText(
                    value = desc,
                    color = MaterialTheme.colors.secondary,
                )
            }

            Container(
                modifier = Modifier.weight(3f)
            ) {
                Row(
                    modifier = Modifier
                        .clip(UiConst.Round.SMALL)
                        .background(MaterialTheme.colors.error),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MediumText(
                        value = model.value,
                        color = MaterialTheme.colors.onError,
                        modifier = Modifier.weight(2f)
                    )
                    Icon(
                        painter = painterResource(id = model.icon),
                        contentDescription = null,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

