package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.data.list.MatchStandings
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchStandingsViewModel : ViewModel() {
    val getDetailLeagueData : LiveData<DetailLeague>
        get() = SaveDetailLeagueData.detailLeague

    private val _MatchStandingsLiveData = MutableLiveData<MatchStandings>()
    val MatchStandingsLiveData : LiveData<MatchStandings>
        get() = _MatchStandingsLiveData

    fun loadMatchStangings(idLeague : String) = GlobalScope.launch(Dispatchers.Main){
        val json = Api.request(Url.matchStandings(idLeague))
        val body = GsonBuilder().create()
            .fromJson(json, MatchStandings::class.java)
        _MatchStandingsLiveData.value = body
    }


}
