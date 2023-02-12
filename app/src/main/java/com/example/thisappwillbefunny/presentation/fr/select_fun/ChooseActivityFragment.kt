package com.example.thisappwillbefunny.presentation.fr.select_fun

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.FeatureElementModel
import com.example.thisappwillbefunny.presentation.navigation.GET_RANDOM_CAT_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.LIKE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_INTERNET_STATUS_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.ExitButton
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.TitleText
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.listOfFeature
import kotlin.system.exitProcess

@Composable
fun ChooseActivityFragment(
    viewModel: ChooseActivityViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, columnFeature, exitButton) = createRefs()

        TitleText(
            value = stringResource(id = R.string.app_name),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = UiConst.Padding.BETWEEN_ELEMENT)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        ColumnFeature(
            modifier = Modifier.constrainAs(columnFeature) {
                top.linkTo(title.bottom, margin = UiConst.Padding.BETWEEN_ELEMENT)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            viewModel = viewModel
        )

        ExitButton(
            modifier = Modifier.constrainAs(exitButton) {
                top.linkTo(parent.top, margin = UiConst.Padding.BETWEEN_ELEMENT)
                end.linkTo(parent.end, margin = UiConst.Padding.MEDIUM)
            },
        ) {
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }


    }
}


@Composable
private fun ColumnFeature(
    modifier: Modifier,
    viewModel: ChooseActivityViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        item { Container { ColumnItem(value = listOfFeature[0], onClick = { viewModel.navigate(GET_RANDOM_CAT_ROUTE) }) } }
        item { Container { ColumnItem(value = listOfFeature[1], onClick = { viewModel.navigate(SELECT_INTERNET_STATUS_ROUTE) }) } }
        item { Container { ColumnItem(value = listOfFeature[2], onClick = { viewModel.navigate(SELECT_ACTIVITY_ROUTE) }) } }
        item { Container { ColumnItem(value = listOfFeature[3], onClick = { viewModel.navigate(LIKE_ACTIVITY_ROUTE) }) } }
    }
}


@Composable
private fun ColumnItem(
    value: FeatureElementModel,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(UiConst.Round.BIG_BLOCK_ROUND)
            .fillMaxWidth()
            .height(UiConst.Size.HEIGHT_FEATURE_EL)
            .background(value.background)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        LargeText(
            value = value.title,
            color = value.textColor
        )
    }
}