package com.mike.musicapp.browse.data

import com.mike.musicapp.common.modules.CategoriesDTO
import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {

    @GET("browse/categories?limit=10")
    suspend fun getCategories(): Response<CategoriesDTO>
}