package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.kade.fragment.data.list.DetailLeague
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailLeagueData

class LeagueDetailViewModel : ViewModel() {
    val getDetailLeagueData : LiveData<DetailLeague> get() = SaveDetailLeagueData.detailLeague
}
