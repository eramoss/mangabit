package com.mangabit.domain

import com.mangabit.domain.manga.Fetcher.Companion.fetchAuthorName
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

    @Test
    fun `should fetch and parse a author name by id`() {
        val authorId = "4deef1dd-1f4d-4064-9879-75de95397964"
        val response = fetchAuthorName(authorId)
        val authorName = Parser.parseAuthorName(response.body!!.string())
        assert(authorName == "Tokino Yousuke")
    }
}