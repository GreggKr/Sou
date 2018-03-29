package me.greggkr.sou.osu

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import me.greggkr.sou.auth.OsuAuthenticator
import me.greggkr.sou.osu.wrappers.Beatmap
import me.greggkr.sou.osu.wrappers.User
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class Osu(key: String) {
    private val baseUrl = "https://osu.ppy.sh/api"
    private val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
    private val client: OkHttpClient = OkHttpClient.Builder()
            .authenticator(OsuAuthenticator(key))
            .build()

    fun getUser(mode: Mode, name: String): User? {
        val req = Request.Builder()
                .url("$baseUrl/get_user?m=${mode.apiValue}&u=$name&type=string")
                .get()
                .build()

//        val res = client.newCall(req).execute()
//
//        val body = res.body() ?: return null
//
//        val str = body.string()
//
//        if (jsonEmpty(str)) return null

        val str = sendRequest(req) ?: return null

        return gson.fromJson(str, Array<User>::class.java)[0]
    }

    fun getBeatmap(mode: Mode, id: Int): Beatmap? {
        val req = Request.Builder()
                .url("$baseUrl/get_beatmaps?m=${mode.apiValue}&u=$id")
                .get()
                .build()

        val str = sendRequest(req) ?: return null

        return gson.fromJson(str, Array<Beatmap>::class.java)[0]
    }

    private fun sendRequest(req: Request): String? {
        val res = client.newCall(req).execute()

        val body = res.body() ?: return null

        val str = body.string()

        if (jsonEmpty(str)) return null

        return str
    }

    private fun jsonEmpty(json: String): Boolean {
        if (gson.fromJson(json, JsonElement::class.java).isJsonObject) return true

        val array = JSONArray(json)

        return array.isNull(0)
    }
}
