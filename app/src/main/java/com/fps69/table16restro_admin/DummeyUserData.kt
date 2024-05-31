package com.fps69.table16restro_admin

data class DummeyUserData(
    val limit: Int,
    val recipes: List<RecipeDummyUserData>,
    val skip: Int,
    val total: Int
)
