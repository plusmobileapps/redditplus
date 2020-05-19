package com.plusmobileapps.reddit.ui.home

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.navigation.NavController
import com.plusmobileapps.reddit.ui.postdetail.navigateToPostDetailFragment
import com.plusmobileapps.reddit.ui.postoptions.navigateToPostOptionsBottomsheet

class HomeFeedRouter(private val navController: NavController, private val activity: Activity) {

    fun openPostOptions(postId: String) = navController.navigateToPostOptionsBottomsheet(postId)

    fun openPostDetails(postId: String, imageview: ImageView) = navController.navigateToPostDetailFragment(postId, imageview)

    fun openShareSheet(link: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        activity.startActivity(shareIntent)
    }

}