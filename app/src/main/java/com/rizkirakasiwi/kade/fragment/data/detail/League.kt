package com.rizkirakasiwi.kade.fragment.data.detail


import com.google.gson.annotations.SerializedName

data class League(
    val idLeague: String,

    @SerializedName("strBadge")
    val logo: String,

    @SerializedName("strCountry")
    val country: String,

    @SerializedName("strLeague")
    val leagueName: String,

    @SerializedName("strSport")
    val sportType: String,

    @SerializedName("strWebsite")
    val website: String,

    @SerializedName("strDescriptionEN")
    val description:String
)