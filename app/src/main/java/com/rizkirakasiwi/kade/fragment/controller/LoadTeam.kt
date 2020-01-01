package com.rizkirakasiwi.kade.fragment.controller

import com.google.gson.GsonBuilder
import com.rizkirakasiwi.kade.fragment.api.Api
import com.rizkirakasiwi.kade.fragment.api.Url
import com.rizkirakasiwi.kade.fragment.data.list.Teams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LoadTeam {

    suspend fun getTeam(idTeam:String) = withContext(Dispatchers.IO){
        val json = Api.request(Url.teamDetail(idTeam))
        val body = GsonBuilder().create()
            .fromJson(json, Teams::class.java)
        return@withContext body.teams[0]
    }

}