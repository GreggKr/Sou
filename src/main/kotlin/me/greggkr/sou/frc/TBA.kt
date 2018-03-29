package me.greggkr.sou.frc

import com.google.gson.GsonBuilder
import me.greggkr.sou.auth.TBAAuthenticator
import me.greggkr.sou.frc.wrappers.FRCRobot
import me.greggkr.sou.frc.wrappers.FRCTeam
import me.greggkr.sou.frc.wrappers.TBAStatus
import okhttp3.OkHttpClient
import okhttp3.Request


class TBA(key: String) {
    private val baseUrl: String = "https://www.thebluealliance.com/api/v3"
    private val gson = GsonBuilder().setPrettyPrinting().setLenient().create()
    private val client: OkHttpClient = OkHttpClient.Builder()
            .authenticator(TBAAuthenticator(key))
            .build()

    fun getTBAStatus(): TBAStatus? {
        val req = Request.Builder()
                .url("$baseUrl/status")
                .get()
                .build()

        val res = client.newCall(req).execute()

        val body = res.body() ?: return null

        val str = body.string()

        return gson.fromJson(str, TBAStatus::class.java)
    }

    fun getTeam(number: Int): FRCTeam? {
        val req = Request.Builder()
                .url("$baseUrl/team/frc$number")
                .get()
                .build()

        val res = client.newCall(req).execute()

        val body = res.body() ?: return null

        val str = body.string()

        return gson.fromJson(str, FRCTeam::class.java)
    }

    fun getRobots(team: Int): Array<FRCRobot>? {

        val req = Request.Builder()
                .url("$baseUrl/team/frc$team/robots")
                .get()
                .build()

        val res = client.newCall(req).execute()

        val body = res.body() ?: return null

        val str = body.string()

        return gson.fromJson(str, Array<FRCRobot>::class.java)
    }

    fun getRobot(team: Int, year: Int): FRCRobot? {
        val robots = getRobots(team) ?: return null

        for (r in robots) {
            if (r.year == year) return r
        }

        return null
    }
}