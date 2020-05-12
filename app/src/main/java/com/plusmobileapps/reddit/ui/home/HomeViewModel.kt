package com.plusmobileapps.reddit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.plusmobileapps.reddit.data.FeedRepository

class HomeViewModel : ViewModel(), RedditFeedItemListener {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment updated"
    }
    val text: LiveData<String> = _text
    val feed: LiveData<List<RedditFeedItem>>
        get() = Transformations.map(FeedRepository.feed) {
            it.map {  redditPost ->
                RedditFeedItem(
                    id = redditPost.id,
                    description = redditPost.description,
                    karmaCount = redditPost.karmaCount,
                    subreddit = redditPost.subreddit,
                    subredditImageUrl = redditPost.subredditImageUrl,
                    timePosted = redditPost.timePosted,
                    title = redditPost.title,
                    username = redditPost.username
                )
            }
        }

    override fun onMoreOptionsClicked(id: String) {

    }

    override fun onPostClicked(id: String) {
    }

    override fun onUpVoteClicked(id: String) {
    }

    override fun onDownVoteClicked(id: String) {
    }

    override fun onCommentClicked(id: String) {
    }

    override fun onShareButtonClicked(id: String) {
    }
}