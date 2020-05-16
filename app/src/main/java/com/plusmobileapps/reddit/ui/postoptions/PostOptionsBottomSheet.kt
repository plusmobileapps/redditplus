package com.plusmobileapps.reddit.ui.postoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.plusmobileapps.reddit.R

fun NavController.navigateToPostOptionsBottomsheet(postId: String) {
    navigate(R.id.action_navigation_home_to_postOptionsBottomSheet, bundleOf(PostOptionsBottomSheet.POST_ID to postId))
}

class PostOptionsBottomSheet : BottomSheetDialogFragment() {

    companion object {

        const val POST_ID = "POST_OPTION_BOTTOM_SHEET_ID"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_option_bottom_sheet, container, false)
    }

}