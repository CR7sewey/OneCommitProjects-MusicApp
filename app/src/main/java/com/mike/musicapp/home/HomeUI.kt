package com.mike.musicapp.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeUI(texto: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "aaaaaaaaaaaaaaaaaaaaaaaaa",
            modifier = modifier)
        Log.d("HomeUI", "HomeUI called with text: $texto")
    }

}