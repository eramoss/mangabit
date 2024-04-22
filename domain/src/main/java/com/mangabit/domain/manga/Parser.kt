package com.mangabit.domain.manga

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.logging.Logger

data class MangaHandler(
    @SerializedName("data") val data: Data,
){

    data class Data (
        @SerializedName("id") val id: String,
        @SerializedName("attributes") val attributes: Attributes,
        @SerializedName("relationships") val relationships: List<Relationships>,
    )
    data class Attributes (
        @SerializedName("title") val title: LocalizedString?,
        @SerializedName("altTitles") val altTitles: List<LocalizedString>?,
        @SerializedName("description") val description: LocalizedString?,
        @SerializedName("status") val status: String?,
        @SerializedName("tags") val tags: List<Tags>?,
        @SerializedName("Links") val links: Links?,
        @SerializedName("name") val name: LocalizedString?,
    )
    data class LocalizedString (
        @SerializedName("en") val en: String?,
        @SerializedName("jp") val jp: String?,
    )
    data class Tags (
        @SerializedName("id") val id: String?,
        @SerializedName("type") val type: String?,
        @SerializedName("attributes") val attributes: Attributes?,
    )
    data class Links (
        @SerializedName("mal") val malId: String?,
    )
    data class Relationships (
        @SerializedName("id") val id: String?,
        @SerializedName("type") val type: String?,
    )

    fun getFirstAuthorId(): String {
        val result : List<Relationships> = this.data.relationships.filter { it.type == "author" }
        return result[0].id!!
    }

    data class AuthorHandler(
        @SerializedName("data") val data: Data,
    )
}

class Parser {
    companion object {
        fun parseManga(response: String): Manga {

            val mangaHandler = Gson().fromJson(response, MangaHandler::class.java)
            val authorName = mangaHandler.getFirstAuthorId()
            val mangaHandlerData = mangaHandler.data
            return Manga(
                id = mangaHandlerData.id,
                malSource = mangaHandlerData.attributes.links?.malId ?: "",
                title = mangaHandlerData.attributes.title?.en ?: "",
                altTitle = mangaHandlerData.attributes.altTitles?.get(0)?.en ?: "",
                authorId = authorName,
                description = mangaHandlerData.attributes.description?.en ?: "",
                genres = mangaHandlerData.attributes.tags?.map { it.attributes?.name?.en ?: "" } ?: emptyList(),
                status = when (mangaHandlerData.attributes.status) {
                    "ongoing" -> Manga.Status.ONGOING
                    "completed" -> Manga.Status.COMPLETED
                    "hiatus" -> Manga.Status.HIATUS
                    "cancelled" -> Manga.Status.CANCELLED
                    else -> Manga.Status.UNKNOWN
                },
                thumbnailUrl = "",
                chapters = 0,
                favorite = false
            )
        }
    }

}