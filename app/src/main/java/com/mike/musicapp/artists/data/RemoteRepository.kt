package com.mike.musicapp.artists.data

import com.mike.musicapp.common.modules.ArtistAlbumsDTO
import com.mike.musicapp.common.modules.ArtistDTO
import com.mike.musicapp.common.modules.ArtistsDTO

class RemoteRepository(
    private val remoteService: RemoteService
) {
    suspend fun getArtists(): Result<ArtistsDTO?> {
        return try {
            val response = remoteService.getArtists()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtistById(id: String): Result<ArtistDTO?> {
        return try {
            val response = remoteService.getArtistById(id)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtistAlbums(id: String): Result<ArtistAlbumsDTO?> {
        return try {
            val response = remoteService.getArtistAlbums(id)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}