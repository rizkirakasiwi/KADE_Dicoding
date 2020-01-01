package com.rizkirakasiwi.kade.fragment.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague

object SaveDetailLeagueData {
    private val _detailLeague = MutableLiveData<DetailLeague>()
    val detailLeague : LiveData<DetailLeague> get() = _detailLeague

    fun save(mydetailLeague: DetailLeague){
        _detailLeague.value = mydetailLeague
    }
}