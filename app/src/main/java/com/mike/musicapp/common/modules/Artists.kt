package com.mike.musicapp.common.modules

data class ArtistsDTO(
    val artists: List<ArtistDTO>
)

data class ArtistDTO(
    val followers: FollowersDTO,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<ImageDTO>,
    val name: String,
    val popularity: Int,
)

data class FollowersDTO(
    val total: Int
)

data class ImageDTO(
    val height: Int,
    val url: String,
    val width: Int
)