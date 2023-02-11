package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.utils.UiConst
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.presentation.MainActivity
import com.example.thisappwillbefunny.presentation.fr.tip_swipe.TipSwipeRight
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.utils.emptyRequestActivityModel
import com.example.thisappwillbefunny.utils.makeHorizontalLine
import com.example.thisappwillbefunny.utils.swipeRightToReturn
import kotlinx.coroutines.launch


/**
 * Сделать переход на главный экран по свайпу вправо.
 * В первый раз сделать подсказку
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SelectActivityFragment(
    viewModel: SelectActivityViewModel,
    navController: NavController,
    isShowTips: Boolean,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    var isLoadContent by remember { mutableStateOf(false) }
    var bottomSheetContent by remember { mutableStateOf(emptyRequestActivityModel) }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { BottomSheetContainer(content = bottomSheetContent) },
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
            isShowTips = isShowTips
        )
    }


}


@Composable
private fun ActivityFragmentsContent(
    viewModel: SelectActivityViewModel,
    showMoreDetail: (RequestActivityModel) -> Unit,
    navController: NavController,
    isShowTips: Boolean
) {
    var _isShowTips by remember { mutableStateOf(false) }
    var isLoad by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    val animationSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.diy))
    val progress by animateLottieCompositionAsState(
        animationSpec,
        iterations = Integer.MAX_VALUE
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .swipeRightToReturn { navController.navigate(CHOOSE_ACTIVITY_ROUTE) },
        contentAlignment = Alignment.Center
    ){

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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = UiConst.Padding.SMALL)
                            .clickable { showMoreDetail(viewModel.currentRequest[it]) },
                        contentAlignment = Alignment.Center,
                    ) {
                        ActivityItem(
                            modifier = Modifier,
                            viewModel = viewModel,
                            index = it
                        )
                    }
                }
            }
        }

        if (!_isShowTips) {
            TipSwipeRight {
                _isShowTips = !_isShowTips
            }
        }

    }


    LaunchedEffect(key1 = Unit, block = {
        viewModel.currentRequest = viewModel.requestActivity()
        if (viewModel.currentRequest.size == viewModel.defaultCountRequest) {
            isLoad = true
        }
    })
}



@Composable
private fun BottomSheetContainer(content: RequestActivityModel) {
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
        
        BottomSheetContent(value = content)
    }
}

@Composable
private fun BottomSheetContent(value: RequestActivityModel) {
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
            LikeThisActivity(value = value.pricing)
        }

    }
}

@Composable
private fun LikeThisActivity(value: ActivityItemDescModel) {

    val tintIcon by animateColorAsState(targetValue = if(value.isLike.value) Color.Red else Color.Black)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { value.isLike.value = !value.isLike.value }
            .defaultMinSize(minHeight = UiConst.Size.HEIGHT_BOTTOM_SHEET_EL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box( modifier = Modifier.makeHorizontalLine())

        Row(
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = UiConst.Size.HEIGHT_BOTTOM_SHEET_EL),
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
                visible = value.isLike.value,
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



