package com.plusmobileapps.reddit.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plusmobileapps.reddit.data.FeedDataSource
import com.plusmobileapps.reddit.data.RedditPost
import org.koin.core.KoinComponent
import org.koin.core.get

class PostDetailViewModel(postId: String) : ViewModel(), KoinComponent {

    private val _state = MutableLiveData<RedditPost>()
    val state: LiveData<RedditPost>
        get() = _state

    init {
        val post = get<FeedDataSource>().getPost(postId) ?: throw IllegalStateException("No Post Id Found for $postId")
        _state.value = RedditPost(
            id = post.id,
            permaLink = post.permaLink,
            description = post.description,
            karmaCount = post.karmaCount,
            subreddit = post.subreddit,
            subredditImageUrl = post.description,
            timePosted = "",
            title = post.title,
            username = post.username
        )
    }

}