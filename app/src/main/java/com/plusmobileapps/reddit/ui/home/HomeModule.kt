package com.plusmobileapps.reddit.ui.home

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { (router: HomeFeedRouter) -> HomeViewModel(router) }
}