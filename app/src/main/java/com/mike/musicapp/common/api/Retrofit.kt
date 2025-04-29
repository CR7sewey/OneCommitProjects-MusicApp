package com.mike.musicapp.common.api

import com.mike.musicapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://square.github.io/retrofit/
object Retrofit {

    //private lateinit var token: String

    //fun setToken(token: String) {
    //    this.token = token
    //}

    private val httpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = "BQBYqznrBQjm7-eiAq1uw3VvRXA_W8MuKScxR5P57fAke1WjAMw44MrvCi-FGQVXzxcgKfpzm-AcFDKb2DNEUuTkuicL2cwyziU5WkHlXQnyQurER0L4yj5l5fqp0JsGHmoeYcyzPFE"

            clientBuilder.addInterceptor { it ->
                val original: Request = it.request()
                val requestBuilder: Request.Builder = original.newBuilder().header("Authorization",
                    "Bearer $token"
                )
                val request: Request = requestBuilder.build()
                it.proceed(request)
            }
            return  clientBuilder.build()
        }
    private const val BASE_URL = "https://api.spotify.com/v1/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // form-urlencoded
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()) // from text to kotlin list4
            .build()
    }

}

object RetrofitToken {

    val clientID = BuildConfig.CLIENT_ID
    val clientSecret = BuildConfig.CLIENT_SECRET

    private val httpClient: OkHttpClient
        get() {

            val clientBuilder = OkHttpClient.Builder()

            clientBuilder.addInterceptor { it ->
                val original: Request = it.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                val request: Request = requestBuilder.build()
                it.proceed(request)
            }
            return  clientBuilder.build()
        }
    private const val BASE_URL = "https://accounts.spotify.com/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // form-urlencoded
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()) // from text to kotlin list4
            .build()
    }

}