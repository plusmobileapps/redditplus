package com.plusmobileapps.redditclient.auth

/**
 * https://github.com/reddit-archive/reddit/wiki/OAuth2#authorization
 *
 * https://www.reddit.com/api/v1/authorize?client_id=CLIENT_ID&response_type=TYPE&state=RANDOM_STRING&redirect_uri=URI&duration=DURATION&scope=SCOPE_STRING
 */
data class AuthUrl(
    private val clientId: String,
    private val responseType: String = "code",
    private val state: String,
    private val redirectUrl: String,
    private val duration: Duration,
    private val scope: List<Scope>
) {
    enum class Duration(val apiName: String) {
        TEMPORARY("temporary"),
        PERMANENT("permanent")
    }

    enum class Scope(val apiName: String) {
        IDENTITY("identity"),
        EDIT("edit"),
        FLAIR("flair"),
        HISTORY("history"),
        MODCONFIG("modconfig"),
        MODFLAIR("modflair"),
        MODLOG("modlog"),
        MODPOSTS("modposts"),
        MODWIKI("modwiki"),
        MY_SUBREDDITS("mysubreddits"),
        PRIVATE_MESSAGES("privatemessages"),
        READ("read"),
        REPORT("report"),
        SAVE("save"),
        SUBMIT("submit"),
        SUBSCRIBE("subscribe"),
        VOTE("vote"),
        WIKI_EDIT("wikiedit"),
        WIKI_READ("wikiread")
    }

    val formattedUrl: String
        get() {
            val scopeString = StringBuilder()
            scope.forEachIndexed { index, scope ->
                if (index == 0) {
                    scopeString.append(scope.apiName)
                } else {
                    scopeString.append(" ${scope.apiName}")
                }
            }
            return "https://www.reddit.com/api/v1/authorize?client_id=$clientId&response_type=$responseType&state=$state&redirect_uri=$redirectUrl&duration=${duration.apiName}&scope=$scopeString".also { print(it) }
        }

}