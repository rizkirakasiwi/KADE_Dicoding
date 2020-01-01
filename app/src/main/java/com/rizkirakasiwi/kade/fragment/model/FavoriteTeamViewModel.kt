package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.detail.Team

class FavoriteTeamViewModel : ViewModel() {
    private val _favorite = MutableLiveData<MutableList<Team>>()
    val favorite : LiveData<MutableList<Team>> get() = _favorite


    fun setFavorite(team:MutableList<Team>){
        _favorite.value = team
    }
}
