package com.mike.musicapp

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.home.HomeMVVM
import com.mike.musicapp.home.HomeUI

@Composable
fun Navigation(navHostController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {

    val homeMVVM = viewModel<HomeMVVM>()
    val navGraph = navHostController.createGraph(
        startDestination = Screen.DrawerScreens.Home.droute
    ) {
        composable(Screen.DrawerScreens.Home.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Home.title)
            HomeUI(
                texto = "Home",
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Account.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Account.title)
            HomeUI(
                texto = "Account",
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Subscription.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Subscription.title)
            HomeUI(
                texto = "Subscription",
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.AddAccount.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.AddAccount.title)
            Log.d("Navigation fafafa", "${homeMVVM.title.value}")
            HomeUI(
                texto = "Add Account",
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Settings.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Settings.title)
            HomeUI(
                texto = "Settings",
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.HelpAndFeedback.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.HelpAndFeedback.title)
            HomeUI(
                texto = "Help and Feedback",
                modifier = modifier
            )
        }

    }

    NavHost(
        navController = navHostController,
        graph = navGraph,
        modifier = modifier.padding(2.dp)
    )

}