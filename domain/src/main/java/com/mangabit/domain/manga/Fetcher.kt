package com.mangabit.domain.manga

import com.mangabit.domain.Utils.Companion.AUTHOR_URL
import com.mangabit.domain.Utils.Companion.COVER_URL
import com.mangabit.domain.Utils.Companion.MANGA_URL
import okhttp3.Response

class Fetcher {
    companion object {
        fun fetchManga(id: String): Response {
            return okhttp3.OkHttpClient().newCall(
                okhttp3.Request.Builder()
                    .url("$MANGA_URL/$id")
                    .build()
            ).execute()
        }

        fun fetchAuthorName(id: String): Response {
            return okhttp3.OkHttpClient().newCall(
                okhttp3.Request.Builder()
                    .url("$AUTHOR_URL/$id")
                    .build()
            ).execute()
        }

        fun fetchCoverImage(id: String): Response {
            return okhttp3.OkHttpClient().newCall(
                okhttp3.Request.Builder()
                    .url("$COVER_URL/$id")
                    .build()
            ).execute()
        }

    }
}