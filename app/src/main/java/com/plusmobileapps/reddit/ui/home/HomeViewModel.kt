package com.plusmobileapps.reddit.ui.home

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.plusmobileapps.reddit.data.FeedDataSource
import org.koin.core.KoinComponent
import org.koin.core.get

class HomeViewModel(private val router: HomeFeedRouter) : ViewModel(), RedditFeedItemListener, KoinComponent {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment updated"
    }

    private val feedRepository = get<FeedDataSource>()

    val text: LiveData<String> = _text
    val feed: LiveData<List<RedditFeedItem>>
        get() = Transformations.map(feedRepository.feed) {
            it.map { redditPost ->
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
        router.openPostOptions(id)
    }

    override fun onPostClicked(id: String, imageview: ImageView) {
        val post = feedRepository.getPost(id) ?: return
        router.openPostDetails(post.permaLink, imageview)
    }

    override fun onUpVoteClicked(id: String) {
    }

    override fun onDownVoteClicked(id: String) {
    }

    override fun onCommentClicked(id: String) {
    }

    override fun onShareButtonClicked(id: String) {
        val post = feedRepository.getPost(id) ?: return
        router.openShareSheet(post.permaLink)
    }
}