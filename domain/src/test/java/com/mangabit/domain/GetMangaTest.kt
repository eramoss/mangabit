package com.mangabit.domain

import org.junit.Test

import org.junit.Assert.*

class GetMangaUnitTest {
    @Test
    fun `get manga`() {
        val manga = GetManga().await(13)!!
        assertEquals(1, manga.id)
        assertEquals("One Piece", manga.name)
        assertEquals("Eiichiro Oda", manga.author)
        assertEquals("Eiichiro Oda", manga.artist)
        assertEquals("As a child, Monkey D. Luffy was inspired to become a pirate by listening to the tales of the buccaneer 'Red-Haired' Shanks. But his life changed when Luffy accidentally ate the Gum-Gum Devil Fruit and gained the power to stretch like rubber...at the cost of never being able to swim again! Years later, still vowing to become the king of the pirates, Luffy sets out on his adventure...one guy alone in a rowboat, in search of the legendary 'One Piece', said to be the greatest treasure in the world...", manga.description)
        assertEquals(listOf("Action", "Adventure", "Comedy", "Fantasy", "Shounen"), manga.genres)
        assertEquals(Manga.Status.ONGOING, manga.status)
        assertEquals("https://cdn.myanimelist.net/images/manga/2/19807.jpg", manga.thumbnailUrl)
        assertEquals(1, manga.chapters.size)
        val chapter = manga.chapters[0]
        assertEquals(1, chapter.id)
        assertEquals("Romance Dawn", chapter.title)
        assertEquals(1.0f, chapter.number)
        assertEquals("1997-07-22", chapter.uploadDate)
        assertEquals(false, chapter.downloaded)
        assertEquals(1, chapter.pages.size)
        val page = chapter.pages[0]
        assertEquals(1, page.id)
        assertEquals(1, page.number)
        assertEquals("https://cdn.myanimelist.net/images/manga/2/19807.jpg", page.imagePath)
        assertEquals("https://www.example.com/one-piece/romance-dawn", chapter.torrentUrl)
        assertEquals(Chapter.Language.ENGLISH, chapter.language)
    }
}