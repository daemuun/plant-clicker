package com.example.clickerplant

import com.example.clickerplant.data.MockPlantsRepository
import com.example.clickerplant.ui.ClickerViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ClickerViewModelTest {
    val viewModel = ClickerViewModel(MockPlantsRepository())

    @Test
    fun onUserClickCountClicksHasBeenAdded() {
        var currentUiState = viewModel.uiState.value
        val expectedCountClicks = currentUiState.countClicks + 1

        viewModel.onPlantClick()

        currentUiState = viewModel.uiState.value
        val actualCountClicks = currentUiState.countClicks

        assertEquals(expectedCountClicks, actualCountClicks)
    }

    @Test
    fun testForChangingThePlantWhenRequiredNumberOfClicksIsReached() {
        var currentUiState = viewModel.uiState.value
        val expectesPlant =
            viewModel.plants[viewModel.plants.indexOf(currentUiState.currentPlant) + 1]

        repeat(currentUiState.currentPlant.countThisPlantTaps) {
            viewModel.onPlantClick()
            viewModel.checkUpdateThePlant()
        }

        currentUiState = viewModel.uiState.value
        val actualPlant = currentUiState.currentPlant

        assertEquals(expectesPlant, actualPlant)
    }

    @Test
    fun testUpdateTheLastPlant() {
        repeat(viewModel.plants.last().countThisPlantTaps) {
            viewModel.onPlantClick()
            viewModel.checkUpdateThePlant()
        }
        var currentUiState = viewModel.uiState.value
        val expectedPlant = currentUiState.currentPlant

        viewModel.onPlantClick()
        viewModel.checkUpdateThePlant()

        currentUiState = viewModel.uiState.value
        val actualPlant = currentUiState.currentPlant

        assertEquals(expectedPlant, actualPlant)
    }

    @Test
    fun updateCurrentRevenueOnClick() {
        var currentUiState = viewModel.uiState.value
        val expectedRevenue = currentUiState.currentRevenue + currentUiState.currentPlant.plantCost

        viewModel.onPlantClick()

        currentUiState = viewModel.uiState.value
        val actualRevenue = currentUiState.currentRevenue

        assertEquals(expectedRevenue, actualRevenue)
    }
}