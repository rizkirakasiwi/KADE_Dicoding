package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.kade.fragment.data.detail.Team
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailTeamData

class DetailTeamViewModel : ViewModel() {
    val saveDetailTeamData : LiveData<Team> get() = SaveDetailTeamData.teamDetail
}
