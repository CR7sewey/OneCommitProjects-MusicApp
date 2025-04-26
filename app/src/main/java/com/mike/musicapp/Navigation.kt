package com.mike.musicapp

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
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
import com.mike.musicapp.account.AccountUI
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.home.HomeMVVM
import com.mike.musicapp.home.HomeUI
import com.mike.musicapp.subscription.SubscriptionUI

@Composable
fun Navigation(navHostController: NavHostController = rememberNavController(), paddingValues: PaddingValues, modifier: Modifier = Modifier) {

    val homeMVVM = viewModel<HomeMVVM>()
    val navGraph = navHostController.createGraph(
        startDestination = Screen.DrawerScreens.Home.droute
    ) {
        composable(Screen.DrawerScreens.Home.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Home.title)
            homeMVVM.setScreen(Screen.DrawerScreens.Home)
            HomeUI(
                texto = "Home",
                navHostController = navHostController,
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Account.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Account.title)
            homeMVVM.setScreen(Screen.DrawerScreens.Account)
            AccountUI(
                navHostController = navHostController,
                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Subscription.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Subscription.title)
            homeMVVM.setScreen(Screen.DrawerScreens.Subscription)
            SubscriptionUI(modifier = modifier)
        }
        composable(Screen.DrawerScreens.AddAccount.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.AddAccount.title)
            homeMVVM.setScreen(Screen.DrawerScreens.AddAccount)
            Log.d("Navigation fafafa", "${homeMVVM.title.value}")
            HomeUI(
                texto = "Add Account",
                navHostController = navHostController,

                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.Settings.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.Settings.title)
            homeMVVM.setScreen(Screen.DrawerScreens.Settings)
            HomeUI(
                texto = "Settings",
                navHostController = navHostController,

                modifier = modifier
            )
        }
        composable(Screen.DrawerScreens.HelpAndFeedback.droute) {
            homeMVVM.setTitle(Screen.DrawerScreens.HelpAndFeedback.title)
            homeMVVM.setScreen(Screen.DrawerScreens.HelpAndFeedback)
            HomeUI(
                texto = "Help and Feedback",
                navHostController = navHostController,

                modifier = modifier
            )
        }

        composable(Screen.BottomNavScreens.Library.broute) {
            homeMVVM.setTitle(Screen.BottomNavScreens.Library.btitle)
            homeMVVM.setScreen(Screen.BottomNavScreens.Library)
            HomeUI(
                texto = "Library",
                navHostController = navHostController,
                modifier = modifier
            )
        }
        composable(Screen.BottomNavScreens.Browse.broute) {
            homeMVVM.setTitle(Screen.BottomNavScreens.Browse.btitle)
            homeMVVM.setScreen(Screen.BottomNavScreens.Browse)
            HomeUI(
                texto = "Browse",
                navHostController = navHostController,
                modifier = modifier
            )
        }

    }

    NavHost(
        navController = navHostController,
        graph = navGraph,
        modifier = modifier.padding(paddingValues)
    )

}