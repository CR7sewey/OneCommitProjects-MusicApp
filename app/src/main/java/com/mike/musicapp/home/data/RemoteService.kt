package com.mike.musicapp.home.data

import com.mike.musicapp.common.api.CategoriesDTO
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RemoteService {
    @GET("browse/categories")
    suspend fun getCategories(): Response<CategoriesDTO?>

}