package com.plusmobileapps.redditclient.auth

import com.plusmobileapps.redditclient.auth.AuthUrl.Scope.*
import org.junit.Assert.*
import org.junit.Test

class AuthUrlTest {
    @Test
    fun `test formatted url`() {
        val expectedRedirect = "https://plusmobileapps.com/redditplus"
        val expectedClientId = "some_client_id"

        val expectedFormattedUrl = "https://www.reddit.com/api/v1/authorize?client_id=$expectedClientId&response_type=code&state=some_random_string&redirect_uri=$expectedRedirect&duration=permanent&scope=read mysubreddits subscribe edit save submit vote"
        val actual = AuthUrl(
            clientId = expectedClientId,
            redirectUrl = expectedRedirect,
            duration = AuthUrl.Duration.PERMANENT,
            state = "some_random_string",
            responseType = "code",
            scope = listOf(READ, MY_SUBREDDITS, SUBSCRIBE, EDIT, SAVE, SUBMIT, VOTE)
        ).formattedUrl

        assertEquals(expectedFormattedUrl, actual)
    }
}