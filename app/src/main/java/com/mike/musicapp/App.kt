package com.mike.musicapp

import android.app.Application
import com.mike.musicapp.common.api.Retrofit
import com.mike.musicapp.common.api.RetrofitToken
import com.mike.musicapp.common.api.token.AccessToken
import com.mike.musicapp.common.api.token.TokenRepository
import com.mike.musicapp.common.api.token.TokenService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            Singleton.setToken()
        }
    }
}

object Singleton {
    lateinit var token: AccessToken

    val retrofitToken: TokenService by lazy {
        RetrofitToken.retrofit.create(TokenService::class.java)
    }

    val tokenRepository: TokenRepository by lazy {
        TokenRepository(
            tokenService = retrofitToken,
        )
    }

    suspend fun setToken() {
        if (::token.isInitialized && !token.isExpired()) {
            return
        } else {
            token = tokenRepository.getToken(
                clientId = RetrofitToken.clientID,
                clientSecret = RetrofitToken.clientSecret
            )

        }

    }
    fun getToken(): String {
        if (!::token.isInitialized) {
            return ""
        }
        return token.access_token
    }
}