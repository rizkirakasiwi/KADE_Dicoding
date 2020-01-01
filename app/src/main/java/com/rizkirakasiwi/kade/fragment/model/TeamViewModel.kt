package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.data.list.Teams
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    private val _teamLiveData = MutableLiveData<Teams>()
    val teamLiveData : LiveData<Teams>
        get() = _teamLiveData

    val getDetailLeagueData : LiveData<DetailLeague>
        get() = SaveDetailLeagueData.detailLeague


    fun loadTeam(idLeague : String) = GlobalScope.launch(Dispatchers.Main){
        val json = Api.request(Url.teamList(idLeague))
        val body = GsonBuilder().create()
            .fromJson(json, Teams::class.java)
        _teamLiveData.value = body
    }
}
