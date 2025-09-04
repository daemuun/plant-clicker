package com.example.clickerplant

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.clickerplant.data.MockPlantsRepository
import com.example.clickerplant.ui.ClickerScreen
import com.example.clickerplant.ui.ClickerViewModel
import com.example.clickerplant.ui.theme.ClickerPlantTheme
import org.junit.Rule
import org.junit.Test

class ClickerUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun on_user_click_count_clicks_increased() {
        val testViewModel = ClickerViewModel(MockPlantsRepository())
        composeTestRule.setContent {
            ClickerPlantTheme {
                ClickerScreen(testViewModel)
            }
        }

        composeTestRule.onNodeWithContentDescription("Огурец").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Количество кликов: 1").assertExists()
    }

    @Test
    fun on_user_click_updated_revenue() {
        val testViewModel = ClickerViewModel(MockPlantsRepository())
        composeTestRule.setContent {
            ClickerPlantTheme {
                ClickerScreen(testViewModel)
            }
        }

        composeTestRule.onNodeWithContentDescription("Огурец").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Общие продажи: 20").assertExists()
    }

    @Test
    fun after_N_clicks_should_change_plant_image() {
        val testViewModel = ClickerViewModel(MockPlantsRepository())
        composeTestRule.setContent {
            ClickerPlantTheme {
                ClickerScreen(testViewModel)
            }
        }

        repeat(testViewModel.plants.first().countThisPlantTaps) {
            composeTestRule.onNodeWithContentDescription("Огурец").performClick()
            composeTestRule.waitForIdle()
        }

        composeTestRule.onNodeWithContentDescription("Помидор").assertExists()
    }
}