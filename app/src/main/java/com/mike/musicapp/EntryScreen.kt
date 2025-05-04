package com.mike.musicapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mike.musicapp.common.modules.Screen

@Composable
fun EntryScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {

    EntryContent(navHostController)
}

@Composable
private fun EntryContent(navHostController: NavHostController, modifier: Modifier = Modifier) {
    var currentContext = LocalContext.current

    Handler(Looper.getMainLooper()).postDelayed({
        if (currentContext != null) {
            Log.d("AQUI", currentContext.toString())
            navHostController.navigate(route = Screen.DrawerScreens.Home.droute)
        }
    }, 5000)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        modifier = modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.9f))) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Onboarding Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(260.dp)
                .padding(16.dp)
        )
    }


}