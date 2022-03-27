package com.ronny.assignment.models


import androidx.annotation.Keep
import com.ronny.assignment.data.database.Item

@Keep
data class TrendingRepo(
    var incomplete_results: Boolean = false,
    var items: List<Item> = listOf(),
    var total_count: Int = 0
)