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

// --------- Albums ---------

data class ArtistAlbumsDTO(
    val total: Int,
    val items: List<AlbumDTO>
)

data class AlbumDTO(
    val album_type: String,
    val total_tracks: Int,
    val id: String,
    val images: List<ImageDTO>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val artists: List<AlbumArtistsDTO>,
    val is_playable: Boolean,

    )

data class AlbumArtistsDTO(
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)


// ----- Tracks -----

data class ArtistTopTracksDTO(
    val tracks: List<TrackDTO>
)

data class TrackDTO(
    val album: AlbumDTO,
    val artists: List<AlbumArtistsDTO>,
    val id: String,
    val name: String,
    val popularity: Int,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)