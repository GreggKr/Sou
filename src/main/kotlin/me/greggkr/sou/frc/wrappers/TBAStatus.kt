package me.greggkr.sou.frc.wrappers

import com.google.gson.annotations.SerializedName
import java.util.*

data class TBAStatus(
        @SerializedName("current_season") val currentSeason: Int,
        @SerializedName("max_season") val maxSeason: Int,
        @SerializedName("is_datafeed_down") val datafeedDown: Boolean,
        @SerializedName("down_events") val downEvents: Array<String>,
        @SerializedName("ios") val iosData: TBAMobileAppData,
        @SerializedName("android") val andriodData: TBAMobileAppData
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TBAStatus

        if (currentSeason != other.currentSeason) return false
        if (maxSeason != other.maxSeason) return false
        if (datafeedDown != other.datafeedDown) return false
        if (!Arrays.equals(downEvents, other.downEvents)) return false
        if (iosData != other.iosData) return false
        if (andriodData != other.andriodData) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currentSeason
        result = 31 * result + maxSeason
        result = 31 * result + datafeedDown.hashCode()
        result = 31 * result + Arrays.hashCode(downEvents)
        result = 31 * result + iosData.hashCode()
        result = 31 * result + andriodData.hashCode()
        return result
    }
}