package com.mangabit.domain.manga
import java.io.Serializable

data class Manga(
    val id: Long,
    val source: String,
    val name: String,
    val author: String,
    val description: String,
    val genres: List<String>,
    val status: Status,
    val thumbnailUrl: String,
    val chapters: Long,
    val favorite: Boolean,
): Serializable {
    enum class Status {
        ONGOING,
        COMPLETED,
        HIATUS,
        CANCELLED,
        UNKNOWN
    }

}
