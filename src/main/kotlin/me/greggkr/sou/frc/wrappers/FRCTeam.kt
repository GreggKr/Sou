package me.greggkr.sou.frc.wrappers

import com.google.gson.annotations.SerializedName

data class FRCTeam(
        val key: String,
        @SerializedName("team_number") val teamNumber: Int,
        val nickname: String?,
        val name: String?,
        val city: String?,
        @SerializedName("state_prov") val providence: String?,
        val country: String,
        val address: String,
        @SerializedName("postal_code") val postalCode: String,
        @SerializedName("gmaps_place_id") val googleMapsPlaceId: String?,
        val lat: Double?,
        val lng: Double?,
        @SerializedName("location_name") val locationName: String?,
        val website: String?,
        @SerializedName("rookie_year") val firstYear: Int,
        val motto: String?,
        @SerializedName("home_championship") val homeChampionships: Map<String, String>
)