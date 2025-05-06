package com.mike.musicapp.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike.musicapp.browse.data.RemoteRepository
import com.mike.musicapp.browse.data.RemoteService
import com.mike.musicapp.common.api.Retrofit
import com.mike.musicapp.common.modules.CategoriesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BrowseMVVM: ViewModel() {

    private val repository: RemoteRepository
        get() = RemoteRepository(
            remoteService = Retrofit.retrofit.create(RemoteService::class.java)
        )

    private val _categories: MutableStateFlow<CategoriesDTO?> = MutableStateFlow(null)
    val categories: StateFlow<CategoriesDTO?> = _categories


    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategories().onSuccess {
                _categories.value = it
            }.onFailure {
                // Handle error
                _categories.value = null
            }
        }

    }

}