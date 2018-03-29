package me.greggkr.sou.frc.wrappers

import com.google.gson.annotations.SerializedName

data class FRCRobot(
        val year: Int,
        @SerializedName("robot_name") val name: String,
        val key: String,
        @SerializedName("team_key") val teamKey: String
)