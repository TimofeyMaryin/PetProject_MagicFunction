package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.utils.UiConst
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.utils.emptyRequestActivityModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SelectActivityFragment(viewModel: SelectActivityViewModel) {
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
            }
        )
    }


}


@Composable
private fun ActivityFragmentsContent(
    viewModel: SelectActivityViewModel,
    showMoreDetail: (RequestActivityModel) -> Unit
) {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = UiConst.Padding.SMALL)
                        .clickable { showMoreDetail(currentRequests[it]) },
                    contentAlignment = Alignment.Center,
                ) {
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


@Composable
private fun BottomSheetContainer(content: RequestActivityModel) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Transparent),
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
                fontStyle = FontStyle.Italic
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
            BottomSheetContentItem(nameItem = value.accessibleModel.nameTypeActivity, desc = value.accessibleModel.desc )
            BottomSheetContentItem(nameItem = value.participants.nameTypeActivity, desc = value.participants.desc)
            BottomSheetContentItem(nameItem = value.pricing.nameTypeActivity, desc = value.pricing.desc)
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
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(UiConst.Size.LINE_HEIGHT)
            .background(Color.Gray))
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



