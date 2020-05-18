package com.plusmobileapps.reddit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import java.net.Proxy

interface FeedDataSource {
    val feed: LiveData<List<RedditPost>>
    fun getPost(postId: String): RedditPost?
}

object FeedRepository: FeedDataSource {
    private val _feed = MutableLiveData<List<RedditPost>>()
    override val feed: LiveData<List<RedditPost>> get() = _feed

    init {
//        _feed.value = listOf(
//            RedditPost("1", "IntelliJ", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/IntelliJ_IDEA_Logo.svg/1200px-IntelliJ_IDEA_Logo.svg.png", "u/Intellijuser", "2 hours", "first", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "1000"),
//            RedditPost("2", "IntelliJ", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/IntelliJ_IDEA_Logo.svg/1200px-IntelliJ_IDEA_Logo.svg.png", "u/Intellijuser", "2 hours", "Intellij is awesome", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "1000"),
//            RedditPost("3", "IntelliJ", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/IntelliJ_IDEA_Logo.svg/1200px-IntelliJ_IDEA_Logo.svg.png", "u/Intellijuser", "2 hours", "Intellij is awesome", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "1000")
//        )

        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val response = client.get<SubredditFeedResponse>("https://www.reddit.com/r/dankmemes/.json")
            Log.d("drew", response.toString())
            _feed.postValue(response.data.children.map {
                val id = "https://reddit.com${it.data.permalink}"
                RedditPost(
                    id = id,
                    username = "u/${it.data.author}",
                    title = it.data.title,
                    timePosted = it.data.subreddit,
                    subredditImageUrl = "",
                    subreddit = it.data.subreddit_name_prefixed,
                    karmaCount = it.data.ups.toString(),
                    description = it.data.url,
                    permaLink = id
                )
            })
        }
    }

    override fun getPost(postId: String): RedditPost? {
        return _feed.value?.find { it.id == postId }
    }
}