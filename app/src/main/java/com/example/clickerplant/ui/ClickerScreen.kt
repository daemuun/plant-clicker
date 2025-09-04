package com.example.clickerplant.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))) {
                PlantImage(
                    clickerUiState.currentPlant.plantImageId,
                    clickerUiState.currentPlant.plantNameId,
                    onPlantImageClick = {
                        viewModel.onPlantClick()
                        viewModel.checkUpdateThePlant()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
                ClickerInfo(
                    countClicks = clickerUiState.countClicks,
                    currentRevenue = clickerUiState.currentRevenue,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth()
                )
            }
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
    Image(
        painter = painterResource(imageId),
        contentDescription = stringResource(nameId),
        modifier = modifier.clickable(onClick = onPlantImageClick)
    )
}

@Composable
fun ClickerInfo(
    countClicks: Int,
    currentRevenue: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.count_clicks, countClicks),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .align(Alignment.End)
                .padding(dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(
                R.string.current_revenue,
                NumberFormat.getNumberInstance().format(currentRevenue)
            ),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small)),
            textAlign = TextAlign.Center
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