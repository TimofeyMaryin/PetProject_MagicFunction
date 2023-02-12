package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import com.example.thisappwillbefunny.presentation.fr.select_activity.ActivityItem
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.LIKE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.swipeRightToReturn
import kotlinx.coroutines.delay


@Composable fun LikeActivitiesFragment(
    navController: NavController,
    viewModel: LikeActivitiesViewModel
) {
    var count by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .swipeRightToReturn {
                navController.navigate(CHOOSE_ACTIVITY_ROUTE) {
                    popUpTo(LIKE_ACTIVITY_ROUTE) {
                        inclusive = true
                    }
                }
            }
    ) {
        item {
            // This will be header
        }
        items(count) {
            var isShow by remember { mutableStateOf(false) }

            AnimatedVisibility(
                visible = isShow,
                enter = slideInVertically(tween(1000)) + fadeIn(tween(1000))
            ) {
                LikedElement(element = viewModel.getElement(it))
            }

            isShow = true
        }
    }




    LaunchedEffect(key1 = Unit, block = {
        count = viewModel.getCountLikedActivity()
    })

//    LaunchedEffect(key1 = Unit, block = {
//        delay(200)
//        isShow = true
//    })
}


@Composable private fun LikedElement(element: LikeActivitiesEntity) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(UiConst.Padding.SMALL), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
        ) {
            Text(text = element.activity)
            Text(text = element.type)
            Text(text = element.participants.toString())
        }

    }
}