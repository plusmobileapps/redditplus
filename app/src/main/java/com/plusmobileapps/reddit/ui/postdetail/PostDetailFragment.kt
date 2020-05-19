package com.plusmobileapps.reddit.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import coil.api.load
import coil.decode.DataSource
import coil.request.Request
import com.plusmobileapps.reddit.R
import com.plusmobileapps.reddit.util.gifImageLoader
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

fun NavController.navigateToPostDetailFragment(postId: String, imageView: ImageView) {
    val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
    navigate(
        R.id.action_navigation_home_to_postDetailFragment,
        bundleOf(
            PostDetailFragment.POST_DETAIL_FRAGMENT_ID to postId,
            PostDetailFragment.TRANSITION_NAME to imageView.transitionName
        ),
        null,
        extras
    )
}

class PostDetailFragment : Fragment() {

    private val viewModel by viewModel<PostDetailViewModel>{ parametersOf(arguments?.getString("POST_ID"))}

    companion object {
        const val POST_DETAIL_FRAGMENT_ID = "POST_ID"
        const val TRANSITION_NAME = "POST_TRANSITION_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        enterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
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
        val imageView = view.findViewById<ImageView>(R.id.post_detail_image)
        imageView.transitionName = arguments?.getString(TRANSITION_NAME)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            text.text = it.title
            imageView.load(it.subredditImageUrl, view.context.gifImageLoader) {
                lifecycle(this@PostDetailFragment)
                listener(object : Request.Listener {
                    override fun onSuccess(request: Request, source: DataSource) {
                        super.onSuccess(request, source)
                        startPostponedEnterTransition()
                    }

                    override fun onError(request: Request, throwable: Throwable) {
                        super.onError(request, throwable)
                        startPostponedEnterTransition()
                    }
                })
            }
        })

    }
}