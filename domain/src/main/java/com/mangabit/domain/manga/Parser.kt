package com.mangabit.domain.manga

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

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
        @SerializedName("links") val links: Links?,
        @SerializedName("name") val name: LocalizedString?,
    )
    data class LocalizedString (
        @SerializedName("en") val en: String?,
        @SerializedName("ja-ro") val jaRo: String?,
        @SerializedName("ja") val ja: String?,
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
        @SerializedName("data") val data: AuthorData?,
    )

    data class AuthorData(
        @SerializedName("attributes") val attr: AuthorAttr?,
    )
    data class AuthorAttr(
        @SerializedName("name") val name: String?,
    )

    data class CoverHandler(
        @SerializedName("data") val data: CoverData?,
    )

    data class CoverData(
        @SerializedName("attributes") val attributes: CoverAttributes?,
    )

    data class CoverAttributes(
        @SerializedName("fileName") val fileName: String?,
    )

}

class Parser {
    companion object {
        fun parseManga(response: String): Manga {
            val mangaHandler = Gson().fromJson(response, MangaHandler::class.java)
            val coverImageId = mangaHandler.data.relationships.filter { it.type == "cover_art" }[0].id?: ""
            val coverImage = Fetcher.fetchCoverImage(coverImageId).body?.string()?.let { parseCoverImage(it) } ?: ""
            val authorName = Fetcher.fetchAuthorName(mangaHandler.getFirstAuthorId()).body?.string()?.let { parseAuthorName(it) } ?: ""
            val mangaHandlerData = mangaHandler.data
            return Manga(
                id = mangaHandlerData.id,
                malSource = mangaHandlerData.attributes.links?.malId ?: "",
                title = mangaHandlerData.attributes.title?.en ?: "",
                altTitle = mangaHandlerData.attributes.altTitles?.joinToString(", ") {
                    it.jaRo ?: ""
                } ?: "",
                author = authorName,
                description = mangaHandlerData.attributes.description?.en ?: "",
                genres = mangaHandlerData.attributes.tags?.map { it.attributes?.name?.en ?: "" } ?: emptyList(),
                status = when (mangaHandlerData.attributes.status) {
                    "ongoing" -> Manga.Status.ONGOING
                    "completed" -> Manga.Status.COMPLETED
                    "hiatus" -> Manga.Status.HIATUS
                    "cancelled" -> Manga.Status.CANCELLED
                    else -> Manga.Status.UNKNOWN
                },
                thumbnailUrl = coverImage,
                chapters = 0,
                favorite = false
            )
        }
        fun parseAuthorName(response: String) : String {
            val authorHandler = Gson().fromJson(response, MangaHandler.AuthorHandler::class.java)
            return authorHandler.data?.attr?.name?: ""
        }

        fun parseCoverImage(response: String) : String {
            val coverHandler = Gson().fromJson(response, MangaHandler.CoverHandler::class.java)
            return coverHandler.data?.attributes?.fileName?: ""
        }
    }

}