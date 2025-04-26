package com.mike.musicapp.common.modules

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
    val imageUrl: Int = 0,
)