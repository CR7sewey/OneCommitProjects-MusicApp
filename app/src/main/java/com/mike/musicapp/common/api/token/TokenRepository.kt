package com.mike.musicapp.common.api.token

class TokenRepository(
    private val tokenService: TokenService
) {
    suspend fun getToken(clientId: String, clientSecret: String): AccessToken {
        val response = tokenService.getToken(clientId = clientId, clientSecret = clientSecret)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Token not found")
        } else {
            throw Exception("Error getting token: ${response.errorBody()?.string()}")
        }
    }
}