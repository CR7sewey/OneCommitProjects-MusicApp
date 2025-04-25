package com.mike.musicapp.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mike.musicapp.common.UI.DialogUI
import com.mike.musicapp.common.modules.Screen

@Composable
fun HomeUI(texto: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "aaaaaaaaaaaaaaaaaaaaaaaaa",
            modifier = modifier
        )
        Log.d("HomeUI", "HomeUI called with text: $texto")


        var showDialog = remember { mutableStateOf(false) }

        if (texto.contains("Add Account")) {
            showDialog.value = true
        }

        /*
        DialogUI(
            onDismissRequest = {
                showDialog.value = false
                navHostController.navigate(Screen.DrawerScreens.Home.droute)
                               },
            onConfirm = { emailAddress, password ->
                Log.d("HomeUI", "Email: $emailAddress, Password: $password")
                showDialog.value = false
                navHostController.navigate(Screen.DrawerScreens.Home.droute)
            },
            dialogOpen = showDialog
        )
    }
    */
    }


}