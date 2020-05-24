package com.plusmobileapps.redditclient.auth

data class AccessTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: String,
    val refresh_token: String,
    val scope: String
)