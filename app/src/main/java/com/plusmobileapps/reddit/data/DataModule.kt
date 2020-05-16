package com.plusmobileapps.reddit.data

import org.koin.dsl.module

val dataModule = module {
    single<FeedDataSource> { FeedRepository }
}