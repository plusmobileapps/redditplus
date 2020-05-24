package com.plusmobileapps.reddit.ui.home

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.plusmobileapps.reddit.R
import com.plusmobileapps.reddit.data.user.User
import com.plusmobileapps.reddit.ui.CustomTabHelper
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    private val homeViewModel by viewModel<HomeViewModel> {
        parametersOf(
            HomeFeedRouter(
                findNavController(),
                requireActivity()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter = RedditFeedAdapter(homeViewModel)
        val recyclerView: RecyclerView = root.findViewById(R.id.reddit_home_feed)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        homeViewModel.feed.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        homeViewModel.urlLauncher.observe(viewLifecycleOwner, Observer(this::launchUrl))

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun launchUrl(url: String) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)

//        val builder = CustomTabsIntent.Builder()
//
//        // modify toolbar color
//        builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
//
//        // add share button to overflow menu
//        builder.addDefaultShareMenuItem()
//
//        val anotherCustomTab = CustomTabsIntent.Builder().build()
//
//        val requestCode = 100
//        val intent = anotherCustomTab.intent
//        intent.setData(Uri.parse(url))
//
//        val pendingIntent = PendingIntent.getActivity(requireContext(),
//            requestCode,
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT)
//
//        // add menu item to oveflow
//        builder.addMenuItem("Sample item", pendingIntent)
//
//        // menu item icon
//        // val bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
//        // builder.setActionButton(bitmap, "Android", pendingIntent, true)
//
//        // modify back button icon
//        // builder.setCloseButtonIcon(bitmap)
//
//        // show website title
//        builder.setShowTitle(true)
//
//        // animation for enter and exit of tab
//        builder.setStartAnimations(requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)
//        builder.setExitAnimations(requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)
//
//        val customTabsIntent = builder.build()
//
//        // check is chrom available
//        val packageName = customTabHelper.getPackageNameToUse(requireContext(), url)
//
//        if (packageName == null) {
//            // if chrome not available open in web view
////            val intentOpenUri = Intent(this, WebViewActivity::class.java)
////            intentOpenUri.putExtra(WebViewActivity.EXTRA_URL, Uri.parse(MINDORKS_PUBLICATION).toString())
////            startActivity(intentOpenUri)
//        } else {
//            customTabsIntent.intent.setPackage(packageName)
//            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
//        }
    }
}
