package com.mike.musicapp.common.modules

import androidx.annotation.DrawableRes
import com.mike.musicapp.R

sealed class Screen(val route: String, val title: String = "") {
    sealed class DrawerScreens(val droute: String, val dtitle: String = "", @DrawableRes val icon: Int? = null): Screen(droute, dtitle) {
        object Home : DrawerScreens("home", "Home", R.drawable.baseline_home_filled_24)
        object Account : DrawerScreens("account", "Account", R.drawable.baseline_account_circle_24)
        object Subscription : DrawerScreens("subscription", "Subscription", R.drawable.baseline_add_circle_24)
        object AddAccount : DrawerScreens("addAccount", "Add Account", R.drawable.baseline_manage_accounts_24)
        object Settings : DrawerScreens("settings", "Settings", R.drawable.baseline_settings_24)
        object HelpAndFeedback : DrawerScreens("helpAndFeedback", "Help and Feedback", R.drawable.baseline_help_center_24 )
    }

    sealed class BottomNavScreens(val broute: String, val btitle: String = "", @DrawableRes val icon: Int? = null) : Screen(broute, btitle) {
        object Home : BottomNavScreens("home", "Home", R.drawable.baseline_home_filled_24)
        object Library : BottomNavScreens("library", "Library", R.drawable.baseline_library_music_24)
        object Browse : BottomNavScreens("browse", "Browse", R.drawable.baseline_apps_24)
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

val bottomNavIcons = listOf(
    Screen.BottomNavScreens.Home,
    Screen.BottomNavScreens.Browse,
    Screen.BottomNavScreens.Library,
)

object Icons {
    val icons = listOf(
        R.drawable.baseline_settings_24,
        R.drawable.baseline_add_circle_24,
        R.drawable.baseline_home_filled_24,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_manage_accounts_24,
        R.drawable.baseline_help_center_24
        )


}