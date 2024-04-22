package com.mangabit.domain.manga

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MangaHandler(
    @SerializedName("data") val data: Data,
) {

    data class Data(
        @SerializedName("mal_id") val id: Long,
        @SerializedName("url") val source: String,
        @SerializedName("images") val images: Images,
        @SerializedName("title") val name: String,
        @SerializedName("authors") val authors: List<Author>,
        @SerializedName("synopsis") val description: String,
        @SerializedName("genres") val genres: List<Genre>,
        @SerializedName("chapters") val chapters: Long,
        @SerializedName("status") val status: String,
    )
    data class Images(
        @SerializedName("jpg") val jpg: Image,
        @SerializedName("webp") val webp: Image,
    )

    data class Image(
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("small_image_url") val smallImageUrl: String,
        @SerializedName("large_image_url") val largeImageUrl: String,
    )

    data class Genre(
        @SerializedName("mal_id") val id: Long,
        @SerializedName("type") val type: String,
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String,
    )

    data class Author(
        @SerializedName("mal_id") val id: Long,
        @SerializedName("type") val type: String,
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String,
    )
}

class Parser {
    companion object {
        fun parseManga(response: String): Manga {

            val mangaHandler = Gson().fromJson(response, MangaHandler::class.java).data
            return Manga(
                id = mangaHandler.id,
                name = mangaHandler.name,
                source = mangaHandler.source,
                author = mangaHandler.authors.first().name,
                description = mangaHandler.description,
                genres = mangaHandler.genres.map { it.name },
                status = when (mangaHandler.status) {
                    "Publishing" -> Manga.Status.ONGOING
                    "Finished" -> Manga.Status.COMPLETED
                    "Hiatus" -> Manga.Status.HIATUS
                    "Cancelled" -> Manga.Status.CANCELLED
                    else -> Manga.Status.UNKNOWN
                },
                thumbnailUrl = mangaHandler.images.jpg.imageUrl,
                chapters = mangaHandler.chapters.toLong(),
                favorite = false
            )
        }
    }

}