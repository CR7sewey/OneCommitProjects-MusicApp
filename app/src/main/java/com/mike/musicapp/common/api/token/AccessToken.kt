package com.mike.musicapp.common.api.token

data class AccessToken(
    val access_token: String,
    val token_type: String,
    val expires_in: Int
) {
    fun isExpired(): Boolean {
        return expires_in <= 0
    }
}