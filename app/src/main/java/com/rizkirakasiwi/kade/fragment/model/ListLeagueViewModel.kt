package com.rizkirakasiwi.kade.fragment.model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListLeagueViewModel : ViewModel() {
    private val _listLeague = MutableLiveData<List<DetailLeague>>()
    val listLeague : LiveData<List<DetailLeague>> = _listLeague

    fun loadLeague(view:View) = GlobalScope.launch(Dispatchers.Main) {
        val idLeague = view.resources.getStringArray(R.array.idLeague)
        val list = mutableListOf<DetailLeague>()
        for (i in idLeague){
            val json = Api.request(Url.leagueDetail(i))
            val body = GsonBuilder().create().fromJson(json, DetailLeague::class.java)
            list.add(body)
        }
        _listLeague.value = list
    }
}
