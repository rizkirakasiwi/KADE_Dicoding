package com.rizkirakasiwi.kade.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.transaction.SaveDetailMatchData

class DetailMatchViewModel : ViewModel() {
    val detailMatch : LiveData<Event>
        get() = SaveDetailMatchData.matchDetail
}
