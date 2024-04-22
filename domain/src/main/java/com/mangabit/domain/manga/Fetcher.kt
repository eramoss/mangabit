package com.mangabit.domain.manga

import okhttp3.Response

class Fetcher {
    companion object {
        const val api = "https://api.jikan.moe/v4/manga/"
        fun fetchManga(id: Int): Response {
            return okhttp3.OkHttpClient().newCall(
                okhttp3.Request.Builder()
                    .url("$api$id/full")
                    .build()
            ).execute()
        }
    }
}