package com.mike.musicapp.browse.data

import com.mike.musicapp.common.modules.CategoriesDTO

class RemoteRepository(
    private val remoteService: RemoteService
) {

    suspend fun getCategories(): Result<CategoriesDTO?> {
        return try {
            val response = remoteService.getCategories()
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