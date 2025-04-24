package com.mike.musicapp.common.modules

import androidx.annotation.DrawableRes

sealed class Screen(val route: String, val title: String = "") {
    sealed class DrawerScreens(val droute: String, val dtitle: String = "", @DrawableRes val icon: Int? = null): Screen(droute, dtitle) {
        object Home : DrawerScreens("home", "Home")
        object Account : DrawerScreens("account", "Account")
        object Subscription : DrawerScreens("subscription", "Subscription")
        object AddAccount : DrawerScreens("addAccount", "Add Account")
        object Settings : DrawerScreens("settings", "Settings")
        object HelpAndFeedback : DrawerScreens("helpAndFeedback", "Help and Feedback", )
    }

}

val screens = listOf(
    Screen.DrawerScreens.Home,
    Screen.DrawerScreens.Account,
    Screen.DrawerScreens.Subscription,
    Screen.DrawerScreens.AddAccount,
    Screen.DrawerScreens.Settings,
    Screen.DrawerScreens.HelpAndFeedback
)