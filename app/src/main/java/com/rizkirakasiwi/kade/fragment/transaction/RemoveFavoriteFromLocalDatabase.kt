package com.rizkirakasiwi.kade.fragment.transaction

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.ImageView
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import com.rizkirakasiwi.kade.fragment.databaseHelper.database
import com.rizkirakasiwi.kade.fragment.controller.FavoriteImage
import org.jetbrains.anko.db.delete

class RemoveFavoriteFromLocalDatabase(
    private val context: Context,
    private val imageView: ImageView
    ) {

    fun match(idEvent: String, table:String) {
        try {
            FavoriteImage.change(imageView, false)
            context.database.use {
                delete(table,
                    "(${Event.ID_EVENT} = {id})",
                    "id" to idEvent
                    )
            }
        } catch (e: SQLiteConstraintException){
            Log.e("RemoveFavorite", e.localizedMessage!!)
        }
    }

    fun team(id: String){
        try {
            FavoriteImage.change(imageView, false)
            context.database.use {
                delete("Team",
                    "(${Team.ID_TEAM} = {id})", "id" to id)
            }
        }catch (e:SQLiteConstraintException){
            Log.e("RemoveFavorite", e.localizedMessage!!)
        }
    }
}