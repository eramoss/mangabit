package com.mangabit.domain

import com.mangabit.domain.manga.Fetcher.Companion.fetchManga
import com.mangabit.domain.manga.Parser
import org.junit.Test

class FetchAndParseMangaTest {
    @Test
    fun `should fetch a manga from MAL and parse it into a manga`() {
        val mangaId = "72231b89-3934-439b-9d83-0f82e45f01d6"
        val response = fetchManga(mangaId)
        val manga = Parser.parseManga(response.body!!.string())
        assert(manga.id == mangaId)
     }
}