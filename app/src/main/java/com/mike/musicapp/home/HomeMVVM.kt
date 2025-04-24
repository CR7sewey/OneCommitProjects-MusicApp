package com.mike.musicapp.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mike.musicapp.common.modules.Screen

class HomeMVVM: ViewModel() {

    private val _title = mutableStateOf(Screen.DrawerScreens.Home.title)
    val title: State<String> = _title

    fun setTitle(title: String) {
        _title.value = title
    }
}