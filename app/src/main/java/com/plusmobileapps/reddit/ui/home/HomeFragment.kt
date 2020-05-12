package com.plusmobileapps.reddit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plusmobileapps.reddit.R

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()

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
