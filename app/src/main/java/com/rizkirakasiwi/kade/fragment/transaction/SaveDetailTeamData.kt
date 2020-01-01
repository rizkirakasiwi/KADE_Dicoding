package com.rizkirakasiwi.kade.fragment.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizkirakasiwi.kade.fragment.data.detail.Team

object SaveDetailTeamData {
    private val _teamDetail = MutableLiveData<Team>()
    val teamDetail : LiveData<Team> get() = _teamDetail

    fun setMatchDetail(event: Team){
        _teamDetail.value = event
    }
}