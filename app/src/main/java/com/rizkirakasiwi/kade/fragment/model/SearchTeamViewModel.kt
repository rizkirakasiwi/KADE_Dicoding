package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.list.Teams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamViewModel : ViewModel() {
    private val _teamData = MutableLiveData<Teams>()
    val teamData : LiveData<Teams> get() = _teamData

    fun searchTeam(query:String) = GlobalScope.launch(Dispatchers.Main){
        val json = Api.request(Url.searchTeam(query))
        val body = GsonBuilder().create()
            .fromJson(json, Teams::class.java)
        _teamData.value = body
    }
}
