package com.rizkirakasiwi.kade.fragment.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizkirakasiwi.kade.fragment.data.detail.Event

object SaveDetailMatchData {
    private val _matchDetail = MutableLiveData<Event>()
    val matchDetail : LiveData<Event> get() = _matchDetail

    fun setMatchDetail(event:Event){
        _matchDetail.value = event
    }
}