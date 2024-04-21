package com.mangabit.domain

import com.mangabit.domain.Parser
import okhttp3.OkHttpClient
import okhttp3.Request

class GetManga {
    fun await (id: Long): Manga? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.jikan.moe/v4/manga/$id/full")
            .build()

        val response = client.newCall(request).execute()
        return if (response.isSuccessful){
            Parser.parseManga(response.body?.string())
        } else {
            null
        }
    }
}