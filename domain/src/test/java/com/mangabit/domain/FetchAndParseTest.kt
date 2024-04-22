package com.mangabit.domain

import com.mangabit.domain.manga.Fetcher.Companion.fetchManga
import com.mangabit.domain.manga.Parser
import org.junit.Test

class FetchAndParseTest {
    @Test
    fun `should fetch a manga from MAL and parse it into a manga`() {
        val mangaId = 13
        val response = fetchManga(mangaId)
        val manga = Parser.parseManga(response.body!!.string())
        assert(manga.id.toInt() == mangaId)
     }
}