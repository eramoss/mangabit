package com.mangabit.domain.chapter
import java.io.Serializable

data class Chapter(
    val id: Long,
    val title: String,
    val number: Float,
    val uploadDate: String,
    val downloaded: Boolean,
    val pages: Long,
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