package com.rizkirakasiwi.kade.fragment.data.detail


data class Team(
    val idLeague: String,
    val idTeam: String,
    val intStadiumCapacity: String,
    val strCountry: String,
    val strDescriptionEN: String,
    val strStadium: String,
    val strStadiumLocation: String,
    val strTeam: String,
    val strTeamBadge: String,
    val strWebsite: String
){
    companion object{
        val ID_LEAGUE = "idLeague"
        val ID_TEAM = "idTeam"
        val STADIUM_CAPACITY = "intStadiumCapacity"
        val COUNTY = "strCountry"
        val DESCRIPTION = "strDescriptionEN"
        val STADIUM_NAME = "strStadium"
        val STADIUM_LOCATION = "strStadiumLocation"
        val TEAM_NAME = "strTeam"
        val LOGO = "strTeamBadge"
        val WEBSITE = "strWebsite"
    }
}