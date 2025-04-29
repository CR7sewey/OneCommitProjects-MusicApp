package com.mike.musicapp.common.api.token

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenService {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getToken(@Field("grant_type") grantType: String = "client_credentials",
                         @Field("client_id") clientId: String,
                         @Field("client_secret") clientSecret: String): Response<AccessToken>

}
