package com.mike.musicapp.common.modules

import androidx.annotation.DrawableRes

data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String,
    val releaseDate: String,
    val genre: String,
    val trackCount: Int
)

data class Category(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    @DrawableRes val imageUrl: Int = 0,
)

data class LibraryItems(
    val id: String = "",
    val name: String = "",
    @DrawableRes val imageUrl: Int = 0,
)