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

    sealed class BottomModal(val mroute: String, val mtitle: String = "", @DrawableRes val icon: Int? = null) : Screen(mroute, mtitle) {
        object Settings : BottomModal("settings", "Settings", R.drawable.baseline_settings_24)
        object Share : BottomModal("share", "Share", R.drawable.baseline_share_24)
        object HelpAndFeedback : BottomModal("helpAndFeedback", "Help and Feedback", R.drawable.baseline_help_center_24)
    }

    sealed class CategoriesScreen(val croute: String, val ctitle: String = "", @DrawableRes val icon: Int? = null) : Screen(croute, ctitle) {
        object Artists : CategoriesScreen("artists", "Artists") {
            fun createRoute(id: String): String {
                return "$croute/$id"
            }
        }

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

val libraryItems = listOf(
    LibraryItems(
        id = "1",
        name = "Playlist",
        imageUrl = R.drawable.baseline_queue_music_24
    ),
    LibraryItems(
        id = "2",
        name = "Artists",
        imageUrl = R.drawable.baseline_mic_24
    ),
    LibraryItems(
        id = "3",
        name = "Albums",
        imageUrl = R.drawable.baseline_album_24
    ),
    LibraryItems(
        id = "4",
        name = "Songs",
        imageUrl = R.drawable.baseline_mic_24
    ),
    LibraryItems(
        id = "5",
        name = "Genre",
        imageUrl = R.drawable.baseline_style_24
    ),
)

val bottomModalItems = listOf(
    Screen.BottomModal.Settings,
    Screen.BottomModal.Share,
    Screen.BottomModal.HelpAndFeedback
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