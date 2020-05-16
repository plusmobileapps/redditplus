package com.plusmobileapps.reddit.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plusmobileapps.reddit.data.FeedDataSource
import org.koin.core.KoinComponent
import org.koin.core.get

class PostDetailViewModel(postId: String) : ViewModel(), KoinComponent {

    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    init {
        val post = get<FeedDataSource>().getPost(postId) ?: throw IllegalStateException("No Post Id Found for $postId")
        _state.value = post.title
    }

}