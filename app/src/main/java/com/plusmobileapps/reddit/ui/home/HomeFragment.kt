package com.plusmobileapps.reddit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plusmobileapps.reddit.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel> { parametersOf(HomeFeedRouter(findNavController())) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter = RedditFeedAdapter(homeViewModel)
        val recyclerView: RecyclerView = root.findViewById(R.id.reddit_home_feed)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        homeViewModel.feed.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return root
    }
}
