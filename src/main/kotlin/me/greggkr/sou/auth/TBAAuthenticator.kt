package me.greggkr.sou.auth

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TBAAuthenticator(private val key: String) : Authenticator {
    init {
        if (!key.matches(Regex("^[a-zA-Z0-9]{64}$"))) throw RuntimeException("Token is invalid.")
    }

    override fun authenticate(route: Route, response: Response): Request? {
        return response
                .request()
                .newBuilder()
                .header("X-TBA-Auth-Key", key)
                .header("User-Agent", "Dillo/2.0")
                .build()
    }
}