package com.rizkirakasiwi.kade.fragment.databaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import org.jetbrains.anko.db.*

class Favorite private constructor(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favoritedb", null, 1) {
    init {
        instance = this
    }

    companion object {
        private var instance: Favorite? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: Favorite(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable("NextMatch", true,
            Event.DATE to TEXT,
            Event.TEAMB_ID to TEXT,
            Event.ID_EVENT to TEXT + PRIMARY_KEY,
            Event.TEAMA_ID to TEXT,
            Event.ID_LEAGUE to TEXT,
            Event.TEAMB_SCORE to TEXT,
            Event.TEAMA_SCORE to TEXT,
            Event.TEAMB_FORMATION to TEXT,
            Event.TEAMB_REDCARD to TEXT,
            Event.TEAMB_NAME to TEXT,
            Event.TEAMB_YELLOWCARD to TEXT,
            Event.TEAMA_FORMATION to TEXT,
            Event.TEAMA_REDCARD to TEXT,
            Event.TEAMA_NAME to TEXT,
            Event.SPORT to TEXT,
            Event.TEAMA_YELLOWCARD to TEXT,
            Event.TIME to TEXT
            )

        db.createTable("PreviousMatch", true,
            Event.DATE to TEXT,
            Event.TEAMB_ID to TEXT,
            Event.ID_EVENT to TEXT + PRIMARY_KEY,
            Event.TEAMA_ID to TEXT,
            Event.ID_LEAGUE to TEXT,
            Event.TEAMB_SCORE to TEXT,
            Event.TEAMA_SCORE to TEXT,
            Event.TEAMB_FORMATION to TEXT,
            Event.TEAMB_REDCARD to TEXT,
            Event.TEAMB_NAME to TEXT,
            Event.TEAMB_YELLOWCARD to TEXT,
            Event.TEAMA_FORMATION to TEXT,
            Event.TEAMA_REDCARD to TEXT,
            Event.TEAMA_NAME to TEXT,
            Event.SPORT to TEXT,
            Event.TEAMA_YELLOWCARD to TEXT,
            Event.TIME to TEXT
        )

        db.createTable("Team", true,
            Team.ID_TEAM to TEXT + PRIMARY_KEY + UNIQUE,
            Team.ID_LEAGUE to TEXT,
            Team.COUNTY to TEXT,
            Team.DESCRIPTION to TEXT,
            Team.LOGO to TEXT,
            Team.STADIUM_CAPACITY to TEXT,
            Team.STADIUM_LOCATION to TEXT,
            Team.STADIUM_NAME to TEXT,
            Team.TEAM_NAME to TEXT,
            Team.WEBSITE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("NextMatch", true)
        db.dropTable("PreviousMatch", true)
        db.dropTable("Team", true)
    }
}

val Context.database: Favorite
    get() = Favorite.getInstance(this)