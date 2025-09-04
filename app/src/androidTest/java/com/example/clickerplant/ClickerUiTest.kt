package com.example.clickerplant

import android.annotation.SuppressLint
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

    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun on_user_click_count_clicks_increased() {
        composeTestRule.setContent {
            ClickerPlantTheme {
                ClickerScreen(ClickerViewModel(MockPlantsRepository()))
            }
        }

        composeTestRule.onNodeWithContentDescription("Огурец").performClick()
        composeTestRule.onNodeWithText("Количество кликов: 1").assertExists()
    }

    @SuppressLint("ViewModelConstructorInComposable")
    @Test
    fun on_user_click_updated_revenue() {
        composeTestRule.setContent {
            ClickerPlantTheme {
                ClickerScreen(ClickerViewModel(MockPlantsRepository()))
            }
        }

        composeTestRule.onNodeWithContentDescription("Огурец").performClick()
        composeTestRule.onNodeWithText("Общие продажи: 20").assertExists()
    }
}