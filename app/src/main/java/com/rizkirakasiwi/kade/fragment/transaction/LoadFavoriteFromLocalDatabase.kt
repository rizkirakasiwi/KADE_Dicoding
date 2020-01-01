package com.rizkirakasiwi.kade.fragment.transaction

import android.content.Context
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import com.rizkirakasiwi.kade.fragment.databaseHelper.database
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select

class LoadFavoriteFromLocalDatabase(private val context: Context) {

    fun match(table:String):List<Event> = context.database.use {
        val fav = mutableListOf<Event>()
        select(table).parseList(object : MapRowParser<List<Event>> {
            override fun parseRow(columns: Map<String, Any?>): List<Event> {
                val event = Event(
                    columns.getValue(Event.DATE).toString(),
                    columns.getValue(Event.TEAMB_ID).toString(),
                    columns.getValue(Event.ID_EVENT).toString(),
                    columns.getValue(Event.TEAMA_ID).toString(),
                    columns.getValue(Event.ID_LEAGUE).toString(),
                    columns.getValue(Event.TEAMB_SCORE).toString(),
                    columns.getValue(Event.TEAMA_SCORE).toString(),
                    columns.getValue(Event.TEAMB_FORMATION).toString(),
                    columns.getValue(Event.TEAMB_REDCARD).toString(),
                    columns.getValue(Event.TEAMB_NAME).toString(),
                    columns.getValue(Event.TEAMB_YELLOWCARD).toString(),
                    columns.getValue(Event.TEAMA_FORMATION).toString(),
                    columns.getValue(Event.TEAMA_REDCARD).toString(),
                    columns.getValue(Event.TEAMA_NAME).toString(),
                    columns.getValue(Event.SPORT).toString(),
                    columns.getValue(Event.TEAMA_YELLOWCARD).toString(),
                    columns.getValue(Event.TIME).toString()
                )
                fav.add(event)
                return fav
            }
        })
        return@use fav
    }


    fun team():List<Team> = context.database.use {
        val fav = mutableListOf<Team>()
        select("Team")
            .parseList(object : MapRowParser<List<Team>>{
                override fun parseRow(columns: Map<String, Any?>): List<Team> {
                    val team = Team(
                        columns.getValue(Team.ID_LEAGUE).toString(),
                        columns.getValue(Team.ID_TEAM).toString(),
                        columns.getValue(Team.STADIUM_CAPACITY).toString(),
                        columns.getValue(Team.COUNTY).toString(),
                        columns.getValue(Team.DESCRIPTION).toString(),
                        columns.getValue(Team.STADIUM_NAME).toString(),
                        columns.getValue(Team.STADIUM_LOCATION).toString(),
                        columns.getValue(Team.TEAM_NAME).toString(),
                        columns.getValue(Team.LOGO).toString(),
                        columns.getValue(Team.WEBSITE).toString()
                    )
                    fav.add(team)
                    return fav
                }
            })
        return@use fav
    }

}