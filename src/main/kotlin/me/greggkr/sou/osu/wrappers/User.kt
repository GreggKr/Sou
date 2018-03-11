package me.greggkr.sou.osu.wrappers

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("user_id") val id: Int,
        @SerializedName("username") val name: String,
        val count300: Int,
        val count100: Int,
        val count50: Int,
        @SerializedName("playcount") val plays: Int,
        @SerializedName("ranked_score") val rankedScore: Long,
        @SerializedName("total_score") val totalScore: Long,
        @SerializedName("pp_rank") val rank: Int,
        val level: Double,
        @SerializedName("pp_raw") val pp: Double,
        val accuracy: Double,
        @SerializedName("count_rank_ss") val countSS: Int,
        @SerializedName("count_rank_ssh") val countSSH: Int,
        @SerializedName("count_rank_s") val countS: Int,
        @SerializedName("count_rank_sh") val countSH: Int,
        @SerializedName("count_rank_a") val countA: Int,
        val country: String,
        @SerializedName("pp_country_rank") val countryRank: Int
)