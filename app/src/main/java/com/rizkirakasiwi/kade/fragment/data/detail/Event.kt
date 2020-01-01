package com.rizkirakasiwi.kade.fragment.data.detail

data class Event(
    val dateEvent: String?,
    val idAwayTeam: String?,
    val idEvent: String?,
    val idHomeTeam: String?,
    val idLeague: String?,
    val intAwayScore: String?,
    val intHomeScore: String?,
    val strAwayFormation: String?,
    val strAwayRedCards: String?,
    val strAwayTeam: String?,
    val strAwayYellowCards: String?,
    val strHomeFormation: String?,
    val strHomeRedCards: String?,
    val strHomeTeam: String?,
    val strSport:String?,
    val strHomeYellowCards: String?,
    val strTime: String?
){
    companion object{
        val DATE = "dateEvent"
        val TEAMB_ID = "idAwayTeam"
        val ID_EVENT = "idEvent"
        val TEAMA_ID = "idHomeTeam"
        val ID_LEAGUE = "idLeague"
        val TEAMB_SCORE = "intAwayScore"
        val TEAMA_SCORE = "intHomeScore"
        val TEAMB_FORMATION = "strAwayFormation"
        val TEAMB_REDCARD = "strAwayRedCards"
        val TEAMB_NAME = "strAwayTeam"
        val TEAMB_YELLOWCARD = "strAwayYellowCards"
        val TEAMA_FORMATION = "strHomeFormation"
        val TEAMA_REDCARD = "strHomeRedCards"
        val TEAMA_NAME = "strHomeTeam"
        val SPORT = "strSport"
        val TEAMA_YELLOWCARD = "strHomeYellowCards"
        val TIME = "strTime"
    }
}