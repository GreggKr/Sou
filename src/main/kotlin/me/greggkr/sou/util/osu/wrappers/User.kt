package me.greggkr.sou.util.osu.wrappers

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("pp_rank") val rank: String
)