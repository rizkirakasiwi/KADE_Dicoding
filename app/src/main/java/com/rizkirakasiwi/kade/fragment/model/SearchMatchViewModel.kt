package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.data.list.Match
import com.rizkirakasiwi.kade.fragment.data.list.SearchMatchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchMatchViewModel : ViewModel() {
    private val _getMatchData = MutableLiveData<Match?>()
    val getMatchData : LiveData<Match?> get() = _getMatchData

    suspend fun setMatchData(query:String) : SearchMatchData? = withContext(Dispatchers.IO){
        val json = Api.request(Url.searchMatch(query))
        val body = GsonBuilder().create()
            .fromJson(json, SearchMatchData::class.java)
       return@withContext body
    }

    fun initGetMatchData(match: SearchMatchData?){
        val listData = arrayListOf<Event>()
        if (match?.event.isNullOrEmpty()){
            match?.event?.forEach {
                if(it.strSport == "Soccer"){
                    listData.add(it)
                }
            }
        }
        _getMatchData.value = Match(listData)
    }
}
