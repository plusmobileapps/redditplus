package com.plusmobileapps.reddit

import android.app.Application
import com.plusmobileapps.reddit.data.dataModule
import com.plusmobileapps.reddit.ui.home.homeModule
import com.plusmobileapps.reddit.ui.postdetail.postDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditPlusApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, homeModule, postDetailModule)
            androidContext(this@RedditPlusApplication)
        }
    }
}