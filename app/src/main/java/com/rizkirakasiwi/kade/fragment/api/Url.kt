package com.rizkirakasiwi.kade.fragment.api

object Url {
    fun leagueDetail(idLeague:String) = "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=$idLeague"
    fun teamList(idLeague: String) = " https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=$idLeague"
    fun previousMatch(idLeague:String) = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$idLeague"
    fun nextMatch(idLeague: String) = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$idLeague"
    fun matchStandings(idLeague: String) = "https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l=$idLeague"
    fun teamDetail(idTeam: String) = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$idTeam"
    fun matchDetail(idEvent:String) = " https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$idEvent"
    fun searchMatch(query:String) = "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=$query"
    fun searchTeam(query: String) = " https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=$query"
}