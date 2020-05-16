package com.plusmobileapps.reddit.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.plusmobileapps.reddit.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

fun NavController.navigateToPostDetailFragment(postId: String) {
    navigate(
        R.id.action_navigation_home_to_postDetailFragment,
        bundleOf(PostDetailFragment.POST_DETAIL_FRAGMENT_ID to postId)
    )
}

class PostDetailFragment : Fragment() {

    private val viewModel by viewModel<PostDetailViewModel>{ parametersOf(arguments?.getString("POST_ID"))}

    companion object {
        const val POST_DETAIL_FRAGMENT_ID = "POST_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = view.findViewById<TextView>(R.id.post_detail_title)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            text.text = it
        })

    }
}