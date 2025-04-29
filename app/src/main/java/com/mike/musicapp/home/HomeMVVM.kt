package com.mike.musicapp.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike.musicapp.common.api.CategoriesDTO
import com.mike.musicapp.common.api.Retrofit
import com.mike.musicapp.common.api.RetrofitToken
import com.mike.musicapp.common.api.token.TokenService
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.home.data.RemoteRepository
import com.mike.musicapp.home.data.RemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeMVVM: ViewModel() {

    private val remoteRepository: RemoteRepository
        get() = RemoteRepository(
            remoteService = Retrofit.retrofit.create(RemoteService::class.java)
        )

    private val _title = mutableStateOf(Screen.DrawerScreens.Home.title)
    val title: State<String> = _title

    private val _screen = mutableStateOf<Screen>(Screen.DrawerScreens.Home)
    val screen: State<Screen>
        get() = _screen

    private val _categories: MutableStateFlow<CategoriesDTO?> = MutableStateFlow(null)
    val categories: StateFlow<CategoriesDTO?> = _categories


    private val tokenService: TokenService
        get() = RetrofitToken.retrofit.create(TokenService::class.java)

    private val _token = mutableStateOf("")
    val token: State<String> = _token

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setScreen(screen: Screen) {
        _screen.value = screen
    }

    init {
        //getToken()
        Log.d("TAG", "init: ${token.value}")
        fetchCategories()
    }


    private fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            //Retrofit.setToken(token.value)
            var categoriesDTO: CategoriesDTO?
            val res = remoteRepository.getCategories()
            Log.d("TAG", "fetchCategories: ${res.toString()}")
            if (res.isSuccess) {
                categoriesDTO = res.getOrNull()
                Log.d("TAG", "fetchCategories: ${categoriesDTO.toString()}")
                _categories.value = categoriesDTO
            } else {
                // Handle the error
                println("Error fetching categories: ${res.exceptionOrNull()?.message}")
            }

        }
    }

    /*private fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {

            if (token.value.isNotEmpty()) {
                return@launch
            }

            val response = tokenService.getToken(
                clientId = RetrofitToken.clientID,
                clientSecret = RetrofitToken.clientSecret,
                grantType = "client_credentials"
            )

                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    if (tokenResponse != null) {
                        _token.value = tokenResponse.access_token
                        Log.d("TAG", "getToken: ${tokenResponse.access_token}")
                        //Retrofit.setToken(tokenResponse.access_token)
                    }
                } else {
                    // Handle the error
                    println("Error fetching token: ${response.errorBody()}")
                }

        }
    }*/

}