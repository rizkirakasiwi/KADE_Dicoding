package com.rizkirakasiwi.kade.fragment.model

import android.util.EventLog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.list.Match

class FavoriteNextMatchViewModel : ViewModel() {
    private val _favorite = MutableLiveData<MutableList<Event>>()
    val favorite : LiveData<MutableList<Event>> get() = _favorite


    fun setFavorite(match:MutableList<Event>){
        _favorite.value = match
    }
}
