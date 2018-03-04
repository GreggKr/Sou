package me.greggkr.sou.util

import com.google.gson.GsonBuilder
import me.greggkr.sou.Sou
import me.greggkr.sou.auth.OsuAuthenticator
import me.greggkr.sou.util.osu.Mode
import me.greggkr.sou.util.osu.wrappers.User
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class Osu(key: String) {
    private val baseUrl = "https://osu.ppy.sh/api"
    private val gson = GsonBuilder().setPrettyPrinting().create()
    val client: OkHttpClient = OkHttpClient.Builder()
            .authenticator(OsuAuthenticator(Sou.config.getProperty("osu-token")))
            .build()

    fun getUser(mode: Mode, name: String): User? {
        val req = Request.Builder()
                .url("$baseUrl/get_user/?m=${mode.apiValue}&u=$name&type=string")
                .get()
                .build()

        val res = client.newCall(req).execute()

        if (res.body() == null || jsonEmpty(res.body()!!.string())) return null

        return gson.fromJson(res.body()!!.string(), Array<User>::class.java)[0]
    }

    private fun jsonEmpty(json: String): Boolean {
        val array = JSONArray(json)

        return array[0] != null
    }
}
