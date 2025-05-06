package com.mike.musicapp.home.data

import android.util.Log
import com.mike.musicapp.common.api.CategoriesDTO

class RemoteRepository(
    private val remoteService: RemoteService
) {
    suspend fun getCategories(): Result<CategoriesDTO?> {
        return try {
            val response = remoteService.getCategories()
            Log.d("RemoteRepository", "Response code: ${response.code().toString()}")
            if (response.isSuccessful) {
                Log.d("RemoteRepository", "Response: ${response.body().toString()}")
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error fetching categories"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}