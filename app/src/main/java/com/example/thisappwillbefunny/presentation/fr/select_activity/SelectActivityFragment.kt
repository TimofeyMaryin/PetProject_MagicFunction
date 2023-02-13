package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.ActivityItem
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Сделать переход на главный экран по свайпу вправо.
 * В первый раз сделать подсказку
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SelectActivityFragment(
    viewModel: SelectActivityViewModel,
    navController: NavController
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    var isLoadContent by remember { mutableStateOf(false) }
    var bottomSheetContent by remember { mutableStateOf(emptyRequestActivityModel) }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { BottomSheetContainer(content = bottomSheetContent, viewModel = viewModel) },
        sheetPeekHeight = UiConst.Padding.ZERO
    ) {

        ActivityFragmentsContent(
            navController = navController,
            viewModel = viewModel,
            showMoreDetail = { content ->
                bottomSheetContent = content
                isLoadContent = true

                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed && isLoadContent) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            },
        )
    }


}


@Composable
private fun ActivityFragmentsContent(
    viewModel: SelectActivityViewModel,
    showMoreDetail: (RequestActivityModel) -> Unit,
    navController: NavController,
) {

    var isLoad by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current


    Box(
        modifier = Modifier.createStartFragment(navController = navController),
        contentAlignment = Alignment.TopCenter
    ){

        if (isLoad) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (text, img) = createRefs()

                AsyncImage(
                    model = "https://i.pinimg.com/564x/db/9f/53/db9f532884d79e584d45825b84d090e9.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(img) { top.linkTo(parent.top) },
                    contentScale = ContentScale.FillWidth
                )
                LargeText(
                    value = "Find your self activity",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .constrainAs(text) {
                            top.linkTo(parent.top, margin = UiConst.Size.MIN_HEIGHT_CAROUSEL_ITEM)
                            start.linkTo(parent.start, margin = UiConst.Padding.MEDIUM)
                        }
                        .fillMaxWidth(.4f),
                    fontFamily = null,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(configuration.screenWidthDp.dp)
                        .background(Color.Transparent))
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = (configuration.screenHeightDp - configuration.screenWidthDp).dp)
                            .padding(UiConst.Padding.SMALL),
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        topStart = UiConst.Size.BtnIconSize,
                                        topEnd = UiConst.Size.BtnIconSize
                                    )
                                )
                                .fillMaxWidth()
                                .background(Color.Black),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            for (i in 0 until viewModel.defaultCountRequest) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(UiConst.Padding.SMALL)
                                        .clickable { showMoreDetail(viewModel.currentRequest[i]) },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    ActivityItem(
                                        modifier = Modifier,
                                        viewModel = viewModel,
                                        index = i
                                    )
                                }
                            }
                        }

                    }


                }
                item {
                    Box(modifier = Modifier.fillMaxWidth().height(UiConst.Size.HEIGHT_FEATURE_EL))
                }
            }

        } else { LoadingFragment(navController = navController) }


    }


    LaunchedEffect(key1 = Unit, block = {
        viewModel.currentRequest = viewModel.requestActivity()
        if (viewModel.currentRequest.size == viewModel.defaultCountRequest) {
            isLoad = true

            Log.e("ActivityFragmentsContent", viewModel.showAnim.toString(), )
            delay(600)
            viewModel.showAnim = true
            Log.e("ActivityFragmentsContent", viewModel.showAnim.toString(), )
        }
    })
}



@Composable
private fun BottomSheetContainer(content: RequestActivityModel, viewModel: SelectActivityViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(
                    width = UiConst.Size.BOTTOM_SHEET_DETAIL.first,
                    height = UiConst.Size.BOTTOM_SHEET_DETAIL.second
                )
                .background(MaterialTheme.colors.onSurface)
                .padding(bottom = UiConst.Padding.SMALL)
        )
        
        BottomSheetContent(value = content, viewModel = viewModel)
    }
}

@Composable
private fun BottomSheetContent(
    value: RequestActivityModel,
    viewModel: SelectActivityViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (title, contents) = createRefs()

        Container(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        ) {
            LargeText(
                value = value.activityPOJO.activity,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(contents) {
                    top.linkTo(title.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            BottomSheetContentItem(nameItem = value.accessibleModel.nameTypeActivity, desc = stringResource(id = value.accessibleModel.desc) )
            BottomSheetContentItem(nameItem = value.participants.nameTypeActivity, desc = "${stringResource(id = value.participants.desc)}(${value.activityPOJO.participants}) ")
            BottomSheetContentItem(nameItem = value.pricing.nameTypeActivity, desc = stringResource(id = value.pricing.desc))
            LikeThisActivity(
                value = value,
                viewModel = viewModel
            )
        }

    }
}

@Composable
private fun LikeThisActivity(
    value: RequestActivityModel,
    viewModel: SelectActivityViewModel
) {

    val tintIcon by animateColorAsState(targetValue = if(value.pricing.isLike.value) Color.Red else Color.Black)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                value.pricing.isLike.value = !value.pricing.isLike.value
                viewModel.likeActivity(
                    activity = LikeActivitiesEntity(
                        accessibility = value.activityPOJO.accessibility,
                        activity = value.activityPOJO.activity,
                        participants = value.activityPOJO.participants,
                        price = value.activityPOJO.price,
                        type = value.activityPOJO.type,
                        like = value.pricing.isLike.value
                    )
                )
            }
            .defaultMinSize(minHeight = UiConst.Size.HEIGHT_BOTTOM_SHEET_EL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box( modifier = Modifier.makeHorizontalLine())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = UiConst.Size.HEIGHT_BOTTOM_SHEET_EL),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like_svgrepo),
                contentDescription = null,
                tint = tintIcon,
                modifier = Modifier.size(UiConst.Size.SmallIconBgSize)
            )
            AnimatedVisibility(
                visible = value.pricing.isLike.value,
                enter = slideInVertically(tween(300)) + fadeIn(tween(300)),
                exit = slideOutVertically(tween(300)) + fadeOut(tween(300))
            ) {
                MediumText(
                    value = "Liked",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontFamily = null
                )
            }

        }
    }
}


@Composable
private fun BottomSheetContentItem(
    nameItem: String,
    desc: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = UiConst.Size.HEIGHT_BOTTOM_SHEET_EL)
    ) {
        Box(modifier = Modifier.makeHorizontalLine())
        Container {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MediumText(
                    value = nameItem,
                    modifier = Modifier.weight(1.3f),
                    color = MaterialTheme.colors.onBackground
                )

                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .border(UiConst.Border.bottomSheetBorder, CircleShape)
                        .weight(3f),
                    contentAlignment = Alignment.Center
                ) {
                    SmallText(
                        value = desc,
                        modifier = Modifier.padding(UiConst.Padding.MEDIUM),
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}



