package com.mike.musicapp.artists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike.musicapp.artists.data.RemoteRepository
import com.mike.musicapp.artists.data.RemoteService
import com.mike.musicapp.common.api.Retrofit
import com.mike.musicapp.common.modules.ArtistAlbumsDTO
import com.mike.musicapp.common.modules.ArtistDTO
import com.mike.musicapp.common.modules.ArtistsDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtistsMVVM: ViewModel() {

    private val remoteRepository: RemoteRepository
        get() = RemoteRepository(
            remoteService = Retrofit.retrofit.create(RemoteService::class.java)
        )

    private val _artists: MutableStateFlow<ArtistsDTO?> = MutableStateFlow(null)
    val artists: StateFlow<ArtistsDTO?> = _artists

    private val _individualArtist : MutableStateFlow<ArtistDTO?> = MutableStateFlow(null)
    val individualArtist: StateFlow<ArtistDTO?> = _individualArtist

    private val _artistAlbums : MutableStateFlow<ArtistAlbumsDTO?> = MutableStateFlow(null)
    val artistAlbums: StateFlow<ArtistAlbumsDTO?> = _artistAlbums

    init {
        getArtists()
    }

    private fun getArtists() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getArtists()
            Log.d("ArtistsMVVM", "Result: ${result.getOrNull().toString()}")
            if (result.isSuccess) {
                _artists.value = result.getOrNull()
            } else {
                // Handle error
                _artists.value = null
            }
        }
    }

    fun getArtistById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getArtistById(id)
            Log.d("ArtistsMVVM", "Result: ${result.getOrNull().toString()}")
            if (result.isSuccess) {
                _individualArtist.value = result.getOrNull()
            } else {
                // Handle error
                _individualArtist.value = null
            }
        }
    }

    fun getArtistAlbums(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getArtistAlbums(id)
            Log.d("ArtistsMVVM", "Result: ${result.getOrNull().toString()}")
            if (result.isSuccess) {
                _artistAlbums.value = result.getOrNull()
            } else {
                // Handle error
                _individualArtist.value = null
            }
        }
    }

}