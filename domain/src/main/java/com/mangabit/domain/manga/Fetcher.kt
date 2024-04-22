package com.mangabit.domain.manga

import okhttp3.Response

class Fetcher {
    companion object {
        private const val API = "https://api.jikan.moe/v4/manga/"
        fun fetchManga(id: Int): Response {
            return okhttp3.OkHttpClient().newCall(
                okhttp3.Request.Builder()
                    .url(API + id)
                    .build()
            ).execute()
        }
    }
}