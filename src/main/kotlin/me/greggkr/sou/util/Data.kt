package me.greggkr.sou.util

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.awt.Color
import java.io.File
import java.net.SocketTimeoutException
import java.net.URL
import java.util.*

class Data {
    companion object {
        const val CACHE_PATH: String = "cache"

        private val owners: List<Long> = Arrays.asList(
                184041169796333568L
        )
        val color = Color(200, 66, 244)

        fun isOwner(id: Long): Boolean {
            return owners.contains(id)
        }

        fun isOwner(id: String): Boolean {
            return owners.contains(id.toLong())
        }

        fun clearCache() {
            Arrays.stream(File(CACHE_PATH).listFiles()).forEach {
                println("deleted ${it.name}")
                it.delete()
            }
        }

        fun getProfilePicture(user: String): String? {
            lateinit var document: Document
            try {
                document = Jsoup.connect("https://osu.ppy.sh/u/$user")
                        .userAgent("Dillo/2.0")
                        .get()
            } catch (e: SocketTimeoutException) {
                return null
            }

            val errHolder = document.body().select(".paddingboth > h2")
            if (errHolder.size != 0) {
                if (errHolder[0].ownText().contains("The user you are looking for was not found!")) return null
            }

            if (document.body().select(".avatar-holder").size == 0) return null

            return "https://${document.body().select(".avatar-holder > img").attr("src").substring(2)}"
        }

        fun getCachedProfilePicture(id: Int, user: String): File? {
            val file = File("${Data.CACHE_PATH}/$id.jpg")

            if (file.exists()) return file

            val url = getProfilePicture(user) ?: return null

            FileUtils.copyURLToFile(URL(url), file)
            return file
        }
    }
}