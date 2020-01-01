package com.rizkirakasiwi.kade.fragment.transaction

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.ImageView
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import com.rizkirakasiwi.kade.fragment.databaseHelper.database
import com.rizkirakasiwi.kade.fragment.controller.FavoriteImage
import org.jetbrains.anko.db.insert

class SaveFavoriteToLocalDatabase(
    private val context: Context,
    private val imageView: ImageView) {

    fun match(data : Event, table:String){
        try {
            FavoriteImage.change(imageView, true)
            context.database.use {
                insert(
                    table,
                    Event.DATE to data.dateEvent,
                    Event.TEAMB_ID to data.idAwayTeam,
                    Event.ID_EVENT to data.idEvent,
                    Event.TEAMA_ID to data.idHomeTeam,
                    Event.ID_LEAGUE to data.idLeague,
                    Event.TEAMB_SCORE to data.intAwayScore,
                    Event.TEAMA_SCORE to data.intHomeScore,
                    Event.TEAMB_FORMATION to data.strAwayFormation,
                    Event.TEAMB_REDCARD to data.strAwayRedCards,
                    Event.TEAMB_NAME to data.strAwayTeam,
                    Event.TEAMB_YELLOWCARD to data.strAwayYellowCards,
                    Event.TEAMA_FORMATION to data.strHomeFormation,
                    Event.TEAMA_REDCARD to data.strHomeRedCards,
                    Event.TEAMA_NAME to data.strHomeTeam,
                    Event.SPORT to data.strSport,
                    Event.TEAMA_YELLOWCARD to data.strHomeYellowCards,
                    Event.TIME to data.strTime
                )
            }
        }catch (e : SQLiteConstraintException){
            Log.e("SaveFavorite", e.localizedMessage!!)
        }
    }

    fun team(data:Team){
        try {
            FavoriteImage.change(imageView, true)
            context.database.use {
                insert(
                    "Team",
                    Team.ID_TEAM to data.idTeam,
                    Team.TEAM_NAME to data.strTeam,
                    Team.WEBSITE to data.strWebsite,
                    Team.STADIUM_NAME to data.strStadium,
                    Team.STADIUM_LOCATION to data.strStadiumLocation,
                    Team.STADIUM_CAPACITY to data.intStadiumCapacity,
                    Team.LOGO to data.strTeamBadge,
                    Team.DESCRIPTION to data.strDescriptionEN,
                    Team.COUNTY to data.strCountry,
                    Team.ID_LEAGUE to data.idLeague
                )
            }
        }catch (e : SQLiteConstraintException){
            Log.e("SaveFavorite", e.localizedMessage!!)
        }
    }
}