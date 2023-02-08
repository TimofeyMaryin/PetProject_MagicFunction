package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.use_cases.GetCatsDescInternetStatus
import com.example.thisappwillbefunny.presentation.ui.elements.AppButton
import com.example.thisappwillbefunny.domain.model.CatsInternetStatusModel
import com.example.thisappwillbefunny.domain.model.TipInternetStatusModel
import com.example.thisappwillbefunny.presentation.ui.elements.CarouselTips
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.listOfTipsInternetStatus
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectInternetStatusFragment(
    viewModel: SelectInternetStatusViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, content, topBar) = createRefs()
        var showFullSizeImage by remember { mutableStateOf(false) }
        var currentUrl by remember { mutableStateOf("") }






         LazyColumn(
            content = {
                stickyHeader {
                    TopBarInternetStatusFragment(value = listOfTipsInternetStatus[Random.nextInt(0, listOfTipsInternetStatus.size)], modifier = Modifier)
                }
                items(GetCatsDescInternetStatus.internetStatusCodes.size) {
                    CatsInternetStatusItem(
                        value = CatsInternetStatusModel(
                            imageUrl = GetCatsDescInternetStatus.execute(GetCatsDescInternetStatus.internetStatusCodes[it]),
                            statusCode = GetCatsDescInternetStatus.internetStatusCodes[it]
                        ),
                        onClick = {
                            currentUrl = GetCatsDescInternetStatus.execute(GetCatsDescInternetStatus.internetStatusCodes[it])
                            showFullSizeImage = true
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(content) {
                    top.linkTo(topBar.bottom, margin = UiConst.Padding.BETWEEN_ELEMENT)
                }
        )

        if (showFullSizeImage){
            ShowImageFullSize(
                url = currentUrl,
                viewModel = viewModel,
                onBack = { showFullSizeImage = false }
            )
        }
    }
}

@Composable
private fun CatsInternetStatusItem(
    value: CatsInternetStatusModel,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = UiConst.Padding.MEDIUM),
        contentAlignment = Alignment.Center
    ){

        SubcomposeAsyncImage(
            model = value.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
        ) {
            val state = painter.state

            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                CircularProgressIndicator(modifier = Modifier.size(UiConst.Size.BUTTON_HEIGHT))
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }

}


@Deprecated("Тут дизайн дырявый, а переделывать стремно")
@Composable
private fun TopBarSelectInternetStatus(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(UiConst.Size.TOP_BAR_HEIGHT)
            .background(MaterialTheme.colors.onBackground)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Container{
            LargeText(
                value = "Select Internet status",
                fontFamily = Raleway,
                color = MaterialTheme.colors.background
            )
        }

        CarouselTips()

    }
}


@Composable
private fun TopBarInternetStatusFragment(
    value: TipInternetStatusModel,
    modifier: Modifier
) {
    var visibility by remember { mutableStateOf(false) }

    val isLoad: LottieCompositionResult = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.tips_1))
    val progress by animateLottieCompositionAsState(
        composition = isLoad.value,
        iterations = Int.MAX_VALUE
    )


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(UiConst.Size.TOP_BAR_HEIGHT)
            .then(modifier)
    ) {
        val (videoPlace, tipsPlace, descTipsPlace) = createRefs()

        AnimatedVisibility(
            visible = visibility,
            enter = slideInHorizontally() + fadeIn(tween(300)),
            modifier = Modifier
                .size(UiConst.Size.TIPS_VIDEOS)
                .constrainAs(videoPlace) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            LottieAnimation(
                composition = isLoad.value,
                progress = progress,
                alignment = Alignment.TopStart,
                modifier = Modifier.size(UiConst.Size.TIPS_VIDEOS)
            )
        }


        AnimatedVisibility(
            visible = visibility,
            enter = slideInVertically() + fadeIn(tween(300)),
            modifier = Modifier.constrainAs(descTipsPlace) {
                top.linkTo(videoPlace.top)
                start.linkTo(videoPlace.end)
                bottom.linkTo(videoPlace.bottom)
            }
        ) {
            Box(
                modifier = Modifier
                    .clip(UiConst.Round.NORMAL)
                    .background(Color.Red.copy(.8f))
                    .width(UiConst.Size.WIDTH_TIP_EL),
                contentAlignment = Alignment.Center
            ) {
                 MediumText(
                    value = value.tips,
                    fontFamily = Raleway,
                    modifier = Modifier.padding(UiConst.Padding.SMALL),
                    fontWeight = FontWeight.Bold
                )
            }
        }


        AnimatedVisibility(
            visible = visibility,
            enter = slideInVertically { it / 2 } + fadeIn(tween(300)),
            modifier = Modifier.constrainAs(tipsPlace) {
                top.linkTo(descTipsPlace.bottom)
                end.linkTo(descTipsPlace.end)
            }
        ) {
            SmallText(value = value.tipsNumber)
        }
    }



    LaunchedEffect(
        key1 = isLoad.isComplete,
        key2 = Unit,
        block = {
            if (isLoad.isComplete) {
                visibility = true
            }
        }
    )
}


@Composable
private fun ShowImageFullSize(
    url: String,
    viewModel: SelectInternetStatusViewModel,
    onBack: () -> Unit,
) {
    var visibleAnimation by remember { mutableStateOf(false) }
    var canCloseWin by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (image, bottomButtonPlace, iconBack) = createRefs()

        AnimatedVisibility(
            visible = visibleAnimation,
            enter = slideInVertically() + fadeIn(tween(300)),
            exit = slideOutVertically() + fadeOut(tween(300)),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(UiConst.DEFAULT_BLUR),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.5f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }



        }

        AnimatedVisibility(
            visible = visibleAnimation,
            enter = slideInVertically{ it / 2 } + fadeIn(tween(300)),
            exit = slideOutHorizontally { it / 2 } + fadeOut(tween(300)),
            modifier = Modifier.constrainAs(bottomButtonPlace){
                bottom.linkTo(parent.bottom)
            }
        ) {
            BottomButtonPlace(
                modifier = Modifier,
                onDownload = { viewModel.downloadImage() },
                onBack = { viewModel.onExit() }
            )

        }

        IconButton(
            onClick = {
                visibleAnimation = false
                canCloseWin = true
            },
            modifier = Modifier
                .size(UiConst.Size.BtnIconSize)
                .constrainAs(iconBack) {
                    top.linkTo(parent.top, margin = UiConst.Padding.BETWEEN_ELEMENT)
                    start.linkTo(parent.start, margin = UiConst.Padding.BETWEEN_ELEMENT)
                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
            )
        }


    }

    LaunchedEffect(
        key1 = Unit,
        key2 = canCloseWin,
        block = {
            delay(300)
            visibleAnimation = true
            if (canCloseWin) { onBack() }
        })

}

@Composable
private fun BottomButtonPlace(
    modifier: Modifier,
    onDownload: () -> Unit,
    onBack: ()-> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AppButton(
            modifier = Modifier.weight(1f),
            text = "Download",
            onClick = { onDownload() }, bgColor = Color.Green
        )
        AppButton(
            modifier = Modifier.weight(1f),
            text = "Back", onClick = { onBack() }, bgColor = Color.Red
        )
    }
}