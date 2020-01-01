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

class NextMatchViewModel : ViewModel() {
    private val _nextMatchLiveData = MutableLiveData<Match>()
    val nextMatchLiveData : LiveData<Match>
        get() = _nextMatchLiveData

    val getDetailLeagueData : LiveData<DetailLeague>
        get() = SaveDetailLeagueData.detailLeague


    fun loadNextMatch(idLeague : String) = GlobalScope.launch(Dispatchers.Main){
        val json = Api.request(Url.nextMatch(idLeague))


        val body = GsonBuilder().create()
            .fromJson(json, Match::class.java)


        _nextMatchLiveData.value = body
    }
}
