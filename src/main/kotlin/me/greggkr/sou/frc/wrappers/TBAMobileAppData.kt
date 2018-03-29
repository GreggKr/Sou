package me.greggkr.sou.frc.wrappers

import com.google.gson.annotations.SerializedName

data class TBAMobileAppData(
        @SerializedName("min_app_version") val minAppVersion: Int,
        @SerializedName("latest_app_version") val latestAppVersion: Int
)