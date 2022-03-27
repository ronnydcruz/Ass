package com.ronny.assignment.data.database

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class Item(
    var page: Int=0,
    var full_name: String? = "",
    var homepage: String? = "",
    var hooks_url: String? = "",
    var html_url: String? = "",
    @PrimaryKey
    var id: Int = 0,
    var name: String? = "",
    var node_id: String? = "",
    var notifications_url: String? = "",

    @Embedded
    var owner: Owner = Owner(),
    var score: Double? = 0.0,
    var size: Int? = 0,
    var updated_at: String? = "",
    var url: String? = "",
    var visibility: String? = "",
    var watchers: Int? = 0,
    var watchers_count: Int? = 0
)
    @Keep
    data class Owner(
        var avatar_url: String? = "",
        var events_url: String? = "",
        var followers_url: String? = "",
        var following_url: String? = "",
        var gists_url: String? = "",
        var gravatar_id: String? = "",
        var login: String? = "",
        var organizations_url: String? = "",
        var received_events_url: String? = "",
        var repos_url: String? = "",
        var site_admin: Boolean? = false,
        var starred_url: String? = "",
        var subscriptions_url: String? = "",
        var type: String? = "",
    )

