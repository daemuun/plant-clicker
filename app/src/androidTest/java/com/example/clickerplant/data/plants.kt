package com.example.clickerplant.data

import com.example.clickerplant.R
import com.example.clickerplant.model.Plant

class MockPlantsRepository: PlantsRepository {
    override fun getPlants(): List<Plant> {
        return listOf(
            Plant(R.drawable.cucumber, R.string.cucumber, 20, 20),
            Plant(R.drawable.tomato, R.string.tomato, 40, 40)
        )
    }
}