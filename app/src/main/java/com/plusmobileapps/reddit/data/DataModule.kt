package com.plusmobileapps.reddit.data

import androidx.room.Room
import com.plusmobileapps.reddit.data.user.UserDao
import com.plusmobileapps.reddit.data.user.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "appDb.db"
        ).build()
    }
    single<UserDao> { get<AppDatabase>().userDao() }
    single<FeedDataSource> { FeedRepository(get()) }
    single { UserRepository(get(), get()) }
    single<HttpClient> {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }
}