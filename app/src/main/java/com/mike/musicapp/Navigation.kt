package com.mike.musicapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.mike.musicapp.home.HomeUI

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()

    val navGraph = navHostController.createGraph(
        startDestination = "home"
    ) {
        composable("home") {
            HomeUI(
                modifier = modifier
            )
        }

    }

    NavHost(
        navController = navHostController,
        graph = navGraph,
        modifier = modifier
    )

}