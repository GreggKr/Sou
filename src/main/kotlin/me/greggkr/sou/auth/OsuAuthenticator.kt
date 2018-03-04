package me.greggkr.sou.auth

import me.greggkr.sou.exceptions.OsuException
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class OsuAuthenticator(private val key: String) : Authenticator {
    init {
        if (!key.matches(Regex("^[a-z0-9]{40}$"))) throw OsuException("Token is invalid.")
    }

    /**
     * Attaches a query parameter "k" to the value of key
     */
    override fun authenticate(route: Route, response: Response): Request {
        return response
                .request()
                .newBuilder()
                .url(response
                        .request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("k", key).build())
                .build()
    }
}