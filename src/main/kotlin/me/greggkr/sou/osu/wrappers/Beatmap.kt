package me.greggkr.sou.osu.wrappers

import com.google.gson.annotations.SerializedName

data class Beatmap(
        val approved: Int,
        @SerializedName("approved_date") val approvedDate: String,
        @SerializedName("last_update") val lastUpdate: String,
        @SerializedName("artist") val songArtist: String,
        @SerializedName("beatmap_id") val beatmapId: Long,
        @SerializedName("beatmapset_id") val beatmapSetId: Long,
        val bpm: Double,
        @SerializedName("creator") val mapper: String,
        @SerializedName("difficultyrating") val stars: Double,
        @SerializedName("diff_size") val circleSize: Int,
        @SerializedName("diff_overall") val overalDiff: Int,
        @SerializedName("diff_approach") val approachRate: Int,
        @SerializedName("diff_drain") val healthDrain: Int,
        @SerializedName("hit_length") val hitLength: Int,
        val source: String,
        @SerializedName("genre_id") val genreId: Int,
        @SerializedName("language_id") val languageId: Int,
        val title: String,
        @SerializedName("total_length") val totalLength: Int,
        @SerializedName("version") val difficulty: String,
        @SerializedName("file_md5") val md5: String,
        val mode: Int,
        val tags: String,
        @SerializedName("favourite_count") val favorites: Int,
        @SerializedName("playcount") val playCount: Int,
        @SerializedName("passcount") val passCount: Int,
        @SerializedName("max_combo") val maxCombo: Int
)