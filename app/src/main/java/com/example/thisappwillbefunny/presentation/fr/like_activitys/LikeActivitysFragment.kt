package com.example.thisappwillbefunny.presentation.fr.like_activitys

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.LIKE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.createButton
import com.example.thisappwillbefunny.utils.swipeRightToReturn


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
            // TODO(This will be header)
        }
        items(count) {
            var isShow by remember { mutableStateOf(false) }

            AnimatedVisibility(
                visible = isShow,
                enter = slideInVertically(tween(1000)) + fadeIn(tween(1000))
            ) {
                LikedElement(element = viewModel.getElement(it), index = it, viewModel = viewModel)
            }

            isShow = true
        }
    }




    LaunchedEffect(key1 = viewModel.getCountLikedActivity(), block = {
        count = viewModel.getCountLikedActivity()
    })

}


@Composable private fun LikedElement(
    element: LikeActivitiesEntity,
    viewModel: LikeActivitiesViewModel,
    index: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiConst.Padding.SMALL),
        contentAlignment = Alignment.Center,
    ){
        LikedContent(
            element,
            viewModel = viewModel,
            index = index
        )
    }
}


@Composable private fun LikedContent(
    element: LikeActivitiesEntity,
    viewModel: LikeActivitiesViewModel,
    index: Int
) {
    var openDialog by remember { mutableStateOf(false) }
    val currentElement by remember { mutableStateOf(viewModel.activityInfoRepo) }
    Row(
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = UiConst.Size.heightActivityItem)
            .clip(UiConst.Round.SMALL)
            .background(Color.Gray), verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(4f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MediumText(
                value = element.activity,
                fontFamily = null,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = UiConst.Padding.SMALL)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                LikedContentIcon(
                    icon = currentElement.getParticipants(viewModel.getElement(index).participants).icon,
                    bg = currentElement.getParticipants(viewModel.getElement(index).participants).bg
                )

                LikedContentIcon(
                    icon = currentElement.getAccessibility(viewModel.getElement(index).accessibility.toFloat()).icon,
                    bg = currentElement.getAccessibility(viewModel.getElement(index).accessibility.toFloat()).bg
                )

                LikedContentIcon(
                    icon = currentElement.getPrice(viewModel.getElement(index).price.toFloat()).icon,
                    bg = currentElement.getPrice(viewModel.getElement(index).price.toFloat()).bg
                )
            }
        }

        Box(
            modifier = Modifier
                .height(UiConst.Size.WIDTH_TIP_EL)
                .background(Color.DarkGray)
                .clickable { openDialog = true }
                .weight(1.4f),
            contentAlignment = Alignment.Center,
        ){
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Magenta,
                modifier = Modifier.size(UiConst.Size.SmallIconBgSize)
            )
        }

        if (openDialog){
            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(UiConst.Size.HEIGHT_FEATURE_EL), contentAlignment = Alignment.TopStart) {
                            LargeText(
                                value = "Confirm Delete",
                                fontFamily = null,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                },
                text = {
                       MediumText(
                           value = "Bla bla ".repeat(13),
                           color = Color.LightGray
                       )
                },
                buttons = {
                    AlertButtonPlace(
                        onDelete = { viewModel.repo.deleteActivity(element) },
                        onCancel = { openDialog = false }
                    )
                },
                backgroundColor = Color.DarkGray
            )
        }
    }
}



@Composable private fun LikedContentIcon(
    icon: Int,
    bg: Brush
) {

    Box(modifier = Modifier.padding(UiConst.Padding.SMALL), contentAlignment = Alignment.Center){

        Box(
            modifier = Modifier
                .clip(UiConst.Round.SMALL)
                .size(UiConst.Size.SmallIconBgSize)
                .background(bg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = icon
                ),
                contentDescription = null)
        }
    }

}


@Composable private fun AlertButtonPlace(onDelete: () -> Unit, onCancel: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(UiConst.Padding.MEDIUM),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier.createButton { onDelete() },
            contentAlignment = Alignment.Center,
        ) {
            LargeText(
                value = "Delete",
                fontFamily = null,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.createButton { onCancel() },
            contentAlignment = Alignment.Center,
        ) {
            LargeText(
                value = "Cancel",
                fontFamily = null,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


