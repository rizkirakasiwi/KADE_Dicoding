package com.rizkirakasiwi.kade.fragment.controller

import android.content.Context
import android.widget.ImageView
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.transaction.SaveFavoriteToLocalDatabase

object FavoriteImage {
    fun change(imageView: ImageView, isFavorite:Boolean){
        if (isFavorite){
            imageView.setImageResource(R.drawable.ic_favorite)
        }else{
            imageView.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}