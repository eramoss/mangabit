package com.mangabit.domain
import java.io.Serializable

data class Manga(
    val id: Long,
    val name: String,
    val author: String,
    val artist: String,
    val description: String,
    val genres: List<String>,
    val status: Status,
    val thumbnailUrl: String,
    val chapters: List<Chapter>
): Serializable {
    enum class Status {
        ONGOING,
        COMPLETED,
        HIATUS,
        CANCELLED
    }

}

data class Chapter(
    val id: Long,
    val title: String,
    val number: Float,
    val uploadDate: String,
    val downloaded: Boolean,
    val pages: List<Page>,
    val torrentUrl: String,
    val language: Language
): Serializable {
    enum class Language {
        ENGLISH,
        SPANISH,
        PORTUGUESE,
        FRENCH,
        GERMAN,
        ITALIAN,
        RUSSIAN,
        CHINESE,
        JAPANESE,
        KOREAN
    }
}

data class Page(
    val id: Long,
    val number: Int,
    val imagePath: String
): Serializable