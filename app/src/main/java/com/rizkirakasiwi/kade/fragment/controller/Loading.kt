package com.rizkirakasiwi.kade.fragment.controller

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_league_fragment.*

object Loading{

    fun progress(progressBar: ProgressBar, recyclerView: View, isDone:Boolean){
        if(isDone) {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

}