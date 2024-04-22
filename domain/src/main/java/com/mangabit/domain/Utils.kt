package com.mangabit.domain

class Utils {
    companion object {
        const val API = "https://api.mangadex.org"
        const val MANGA_URL = "$API/manga"
        const val CHAPTER_URL = "$API/chapter"
        const val LIST_URL = "$API/list"
        const val CDN_URL = "https://uploads.mangadex.org"
        const val AUTHOR_URL = "$API/author"

         val uuidRegex: Regex
            get() = Regex("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")
    }
}