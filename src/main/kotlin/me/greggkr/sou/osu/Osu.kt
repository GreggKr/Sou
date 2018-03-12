package me.greggkr.sou.osu

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import me.greggkr.sou.auth.OsuAuthenticator
import me.greggkr.sou.osu.wrappers.User
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.commons.io.FileUtils
import org.json.JSONArray
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.net.URL

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

        val res = client.newCall(req).execute()

        val body = res.body() ?: return null

        val str = body.string()

        if (jsonEmpty(str)) return null

        return gson.fromJson(str, Array<User>::class.java)[0]
    }

    private fun jsonEmpty(json: String): Boolean {
        if (gson.fromJson(json, JsonElement::class.java).isJsonObject) return true

        val array = JSONArray(json)

        return array.isNull(0)
    }

    fun getProfilePicture(user: String): String? {
        val document: Document = Jsoup.connect("https://osu.ppy.sh/u/$user")
                .userAgent("Dillo/2.0")
                .get()

        val errHolder = document.body().select(".paddingboth > h2")
        if (errHolder.size != 0) {
            if (errHolder[0].ownText().contains("The user you are looking for was not found!")) return null
        }

        if (document.body().select(".avatar-holder").size == 0) return null

        return "https://${document.body().select(".avatar-holder > img").attr("src").substring(2)}"
    }

    fun getCachedProfilePicture(id: Int, user: String): File? {
        val url = getProfilePicture(user) ?: return null

        val file = File("cache/$id.jpg")

        if (file.exists()) return file

        FileUtils.copyURLToFile(URL(url), file)
        return file
    }
}
