package com.mike.musicapp.artists.data

import com.mike.musicapp.common.modules.ArtistAlbumsDTO
import com.mike.musicapp.common.modules.ArtistDTO
import com.mike.musicapp.common.modules.ArtistsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("artists?ids=2CIMQHirSU0MQqyYHq0eOx%2C57dN52uHvrHOxijzpIgu3E%2C1vCWHaC5f2uS3yhpwWbIA6")
    suspend fun getArtists(): Response<ArtistsDTO>

    @GET("artists/{id}")
    suspend fun getArtistById(@Path("id") id: String): Response<ArtistDTO>

    @GET("artists/{id}/albums?include_groups=single&market=PT&limit=10&offset=1")
    suspend fun getArtistAlbums(@Path("id") id: String): Response<ArtistAlbumsDTO>
}