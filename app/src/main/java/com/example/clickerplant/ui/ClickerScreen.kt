package com.example.clickerplant.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clickerplant.R
import com.example.clickerplant.ui.theme.ClickerPlantTheme
import java.text.NumberFormat

@Composable
fun ClickerScreen(viewModel: ClickerViewModel = viewModel()) {
    val clickerUiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ClickerTopAppBar()
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PlantImage(
                clickerUiState.currentPlant.plantImageId,
                clickerUiState.currentPlant.plantNameId,
                onPlantImageClick = {
                    viewModel.onPlantClick()
                    viewModel.checkUpdateThePlant()
                }
            )
            ClickerInfo(
                countClicks = clickerUiState.countClicks,
                currentRevenue = clickerUiState.currentRevenue,
                modifier = Modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickerTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier
    )
}

@Composable
fun PlantImage(
    @DrawableRes imageId: Int,
    @StringRes nameId: Int,
    onPlantImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(nameId),
            modifier = Modifier.clickable(onClick = onPlantImageClick)
        )
    }
}

@Composable
fun ClickerInfo(
    countClicks: Int,
    currentRevenue: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.count_clicks, countClicks))
        Text(
            text = stringResource(
                R.string.current_revenue,
                NumberFormat.getNumberInstance().format(currentRevenue)
            )
        )
    }
}


@Preview
@Composable
fun ClickerScreenPreview() {
    ClickerPlantTheme {
        ClickerScreen()
    }
}

@Preview
@Composable
fun ClickerScreenDarkThemePreview() {
    ClickerPlantTheme(darkTheme = true) {
        ClickerScreen()
    }
}