package com.plusmobileapps.reddit.data.user

import android.util.Log
import androidx.lifecycle.LiveData
import com.plusmobileapps.reddit.data.AuthHttpClient
import com.plusmobileapps.redditclient.auth.AccessTokenResponse
import com.plusmobileapps.redditclient.auth.AuthUrl
import com.plusmobileapps.redditclient.auth.AuthUrl.Scope
import com.plusmobileapps.redditclient.auth.AuthUrl.Scope.*
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.parametersOf
import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.parameter.parametersOf


class UserRepository(val client: HttpClient, val userDao: UserDao) : KoinComponent {

    companion object {
        private const val CLIENT_ID = "-PKHeIjN-oE15w" //TODO replace with env variable
        private const val REDIRECT_URI = "https://plusmobileapps.com/redditplus"
    }

    val user: LiveData<User> get() = userDao.getUser()
    val authUrl = AuthUrl(
        clientId = CLIENT_ID,
        redirectUrl = REDIRECT_URI,
        duration = AuthUrl.Duration.PERMANENT,
        state = "some_random_string",
        responseType = "code",
        scope = listOf(
            READ,
            MY_SUBREDDITS,
            SUBSCRIBE,
            EDIT,
            SAVE,
            SUBMIT,
            VOTE
        )
    ).formattedUrl

    init {
//        GlobalScope.launch(Dispatchers.IO) {
//            userDao.insertUser(User("1", "Andrew", "34"))
//        }
    }

    suspend fun loginUser(username: String, password: String) {

    }

    fun onUserAuthorized(state: String?, token: String?, error: String?) {
        when (error) {
            "access_denied" -> {
                return
            }
            "unsupported_response_type" -> {
                return
            }
            "invalid_scope" -> {
                return
            }
            "invalid_request" -> {
                return
            }
            else -> {
            }
        }
        state ?: return
        token ?: return

        //user has authorized!!

        //validate state equals the state going out

        GlobalScope.launch(Dispatchers.IO) {

            val accessTokenRequest = get<AuthHttpClient>() {
                parametersOf(CLIENT_ID)
            }.client.post<AccessTokenResponse>("https://www.reddit.com/api/v1/access_token") {
                body = FormDataContent(Parameters.build {
                    append( "grant_type", "authorization_code")
                    append("code", "$token")
                    append("redirect_uri", REDIRECT_URI)
                })
            }
            Log.d("stein", "Access token: $accessTokenRequest")
            val upvoteRequest = client.post<String>("https://oauth.reddit.com/api/vote") {
                header("Authorization", "${accessTokenRequest.token_type} ${accessTokenRequest.access_token}")
                body = FormDataContent(Parameters.build {
                    append("id", "t3_gnb2y8")
                    append("dir", "1")
                })
            }
            Log.d("stein", "Upvote response: $upvoteRequest")
        }

    }

}