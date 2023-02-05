package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.thisappwillbefunny.domain.use_cases.GetCatsDescInternetStatus
import com.example.thisappwillbefunny.presentation.models.AppButton
import com.example.thisappwillbefunny.presentation.models.CatsInternetStatusModel
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.utils.UiConst
import kotlinx.coroutines.delay

@Composable
fun SelectInternetStatusFragment(
    viewModel: SelectInternetStatusViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, content) = createRefs()
        var showFullSizeImage by remember { mutableStateOf(false) }
        var currentUrl by remember { mutableStateOf("") }


        LargeText(value = "Select internet status", modifier = Modifier.constrainAs(title) {
            top.linkTo(parent.top)
        })


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
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
                    top.linkTo(title.bottom, margin = UiConst.Padding.BETWEEN_ELEMENT)
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
    Container(modifier = Modifier.clickable { onClick() }) {
        SubcomposeAsyncImage(
            model = value.imageUrl,
            contentDescription = null,
        ) {
            val state = painter.state

            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }

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
                modifier = Modifier.fillMaxSize().blur(UiConst.DEFAULT_BLUR),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(.5f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(model = url, contentDescription = null)
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

    LaunchedEffect(key1 = Unit, block = { visibleAnimation = true })
    LaunchedEffect(key1 = canCloseWin, block = {
        if (canCloseWin) {
            delay(1000)
            onBack()
        }
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