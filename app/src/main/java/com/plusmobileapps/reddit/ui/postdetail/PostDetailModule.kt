package com.plusmobileapps.reddit.ui.postdetail

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postDetailModule = module {
    viewModel { (id: String) -> PostDetailViewModel(id) }
}