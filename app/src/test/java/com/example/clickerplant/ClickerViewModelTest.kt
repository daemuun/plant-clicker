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
}