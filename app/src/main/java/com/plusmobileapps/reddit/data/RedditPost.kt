package com.plusmobileapps.reddit.data

data class RedditPost(
    val id: String,
    val subreddit: String,
    val subredditImageUrl: String,
    val username: String,
    val timePosted: String,
    val title: String,
    val description: String,
    val karmaCount: String
)