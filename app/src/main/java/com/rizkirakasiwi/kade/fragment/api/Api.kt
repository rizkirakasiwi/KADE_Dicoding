package com.rizkirakasiwi.kade.fragment.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object Api {
    suspend fun request(url: String): String? = withContext(Dispatchers.IO){
        val req = Request.Builder()
            .url(url)
            .build()

        return@withContext OkHttpClient().newCall(req)
            .execute().body?.string()
    }

}
