package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.data.list.Match
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchViewModel : ViewModel() {
    private val _previousMatchLiveData = MutableLiveData<Match>()
    val previousMatchLiveData : LiveData<Match>
        get() = _previousMatchLiveData

    val getDetailLeagueData : LiveData<DetailLeague>
        get() = SaveDetailLeagueData.detailLeague


    fun loadPreviosMatch(idLeague : String) = GlobalScope.launch(Dispatchers.Main){
        val json = Api.request(Url.previousMatch(idLeague))
        val body = GsonBuilder().create()
            .fromJson(json, Match::class.java)
        _previousMatchLiveData.value = body
    }
}
