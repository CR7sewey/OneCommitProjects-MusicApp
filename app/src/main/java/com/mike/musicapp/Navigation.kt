package com.mike.musicapp

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.mike.musicapp.account.AccountUI
import com.mike.musicapp.artists.ArtistUI
import com.mike.musicapp.artists.ArtistsMVVM
import com.mike.musicapp.artists.ArtistsUI
import com.mike.musicapp.browse.BrowseUI
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.home.HomeMVVM
import com.mike.musicapp.home.HomeUI
import com.mike.musicapp.library.LibraryUI
import com.mike.musicapp.subscription.SubscriptionUI

@Composable
fun Navigation(navHostController: NavHostController = rememberNavController(), showTopBottomDrawerBarFunction : () -> Unit, paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    val artistsMVVM = viewModel<ArtistsMVVM>()
    val homeMVVM = viewModel<HomeMVVM>()
    val navGraph = navHostController.createGraph(
        startDestination = "entry" //Screen.DrawerScreens.Home.droute
    ) {
        composable(route = "entry", exitTransition =
            {
                return@composable fadeOut(tween(700))
            }) {
            homeMVVM.setTitle("EntryScreen")
            EntryScreen(
                navHostController = navHostController,
                modifier = modifier
            )
        }

        composable(Screen.DrawerScreens.Home.droute, enterTransition = {
            if (initialState.destination.route == "entry") {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(1000, easing = LinearEasing)
                )

            } else {
                return@composable null
            }

           /* fadeIn(tween(700))
            scaleIn(spring(Spring.DampingRatioHighBouncy))
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
            expandIn(tween(700, easing = LinearEasing))*/
        }) {
            showTopBottomDrawerBarFunction.invoke()
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
            homeMVVM.setTitle(Screen.BottomNavScreens.Library.title)
            homeMVVM.setScreen(Screen.BottomNavScreens.Library)
            LibraryUI(
                modifier = modifier
            )
        }
        composable(Screen.BottomNavScreens.Browse.broute) {
            homeMVVM.setTitle(Screen.BottomNavScreens.Browse.title)
            homeMVVM.setScreen(Screen.BottomNavScreens.Browse)
            BrowseUI(
                texto = "Browse",
                navHostController = navHostController,
                modifier = modifier
            )
        }

        composable(Screen.CategoriesScreen.Artists.croute) {
            homeMVVM.setTitle(Screen.CategoriesScreen.Artists.ctitle)
            homeMVVM.setScreen(Screen.CategoriesScreen.Artists)
            ArtistsUI(
                nvaHostController = navHostController,
                artistsMVVM = artistsMVVM,
                modifier = modifier
            )
        }

        composable(Screen.CategoriesScreen.Artists.createRoute("{id}"), arguments = listOf(navArgument("id"){ type=
            NavType.StringType})) { backStackEntry ->
            homeMVVM.setTitle(Screen.CategoriesScreen.Artists.ctitle)
            homeMVVM.setScreen(Screen.CategoriesScreen.Artists)
            val id = requireNotNull(backStackEntry.arguments?.getString("id"))
            ArtistUI(
                id,
                artistsMVVM = artistsMVVM,
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