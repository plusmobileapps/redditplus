package com.plusmobileapps.reddit.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature

class AuthHttpClient(username: String) {

    val client: HttpClient = HttpClient(Android) {
        install(Auth) {
            basic {
                this.username = username
                this.password = ""
            }
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

}