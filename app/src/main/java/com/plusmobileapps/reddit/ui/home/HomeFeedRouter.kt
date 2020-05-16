package com.plusmobileapps.reddit.ui.home

import androidx.navigation.NavController
import com.plusmobileapps.reddit.ui.postdetail.navigateToPostDetailFragment
import com.plusmobileapps.reddit.ui.postoptions.navigateToPostOptionsBottomsheet

class HomeFeedRouter(private val navController: NavController) {

    fun openPostOptions(postId: String) = navController.navigateToPostOptionsBottomsheet(postId)

    fun openPostDetails(postId: String) = navController.navigateToPostDetailFragment(postId)

}