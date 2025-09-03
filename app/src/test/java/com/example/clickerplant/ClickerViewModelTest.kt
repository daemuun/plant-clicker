package com.example.clickerplant

import com.example.clickerplant.data.MockPlantsRepository
import com.example.clickerplant.ui.ClickerViewModel
import org.junit.Test
import org.junit.Assert.*

class ClickerViewModelTest {
    val viewModel = ClickerViewModel(MockPlantsRepository())

    @Test
    fun `on user click count clicks has been added`() {
        var currentUiState = viewModel.uiState.value
        val expectedCountClicks = currentUiState.countClicks + 1

        viewModel.onPlantClick()

        currentUiState = viewModel.uiState.value
        val actualCountClicks = currentUiState.countClicks

        assertEquals(expectedCountClicks, actualCountClicks)
    }

    @Test
    fun `test for changing the plant when the required number of clicks is reached`() {
        var currentUiState = viewModel.uiState.value
        val expectesPlant = viewModel.plants[viewModel.plants.indexOf(currentUiState.currentPlant) + 1]

        repeat(currentUiState.currentPlant.countThisPlant) {
            viewModel.onPlantClick()
            viewModel.checkUpdateThePlant()
        }

        currentUiState = viewModel.uiState.value
        val actualPlant = currentUiState.currentPlant

        assertEquals(expectesPlant, actualPlant)
    }

    @Test
    fun `test update the last plant`() {
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
}