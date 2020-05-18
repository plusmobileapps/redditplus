package com.plusmobileapps.reddit.data

import java.io.Serializable

data class SubredditFeedResponse(
    val kind: String,
    val data: FeedData
): Serializable {
    data class FeedData(
        val children: List<Post>
    ) {
        data class Post(
            val kind: String,
            val data: Data
        ) {
            data class Data(
                val subreddit: String,
                val author_fullname: String,
                val title: String,
                val subreddit_name_prefixed: String,
                val downs: Int,
                val ups: Int,
                val thumbnail: String,
                val post_hint: String,
                val url: String,
                val num_comments: Int,
                val author: String,
                val permalink: String,
                val is_video: Boolean
            )
        }
    }
}