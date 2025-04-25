package com.mike.musicapp.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mike.musicapp.common.modules.Screen

class HomeMVVM: ViewModel() {

    private val _title = mutableStateOf(Screen.DrawerScreens.Home.title)
    val title: State<String> = _title

    private val _screen = mutableStateOf<Screen>(Screen.DrawerScreens.Home)
    val screen: State<Screen>
        get() = _screen

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setScreen(screen: Screen) {
        _screen.value = screen
    }

}