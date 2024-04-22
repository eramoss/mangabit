package com.mangabit.domain.page
import java.io.Serializable

data class Page(
    val id: Long,
    val number: Int,
    val imagePath: String
): Serializable