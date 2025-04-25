package com.mike.musicapp.common.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.home.HomeMVVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDrawerExample(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState : androidx.compose.material3.DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    navHostController: NavHostController,
    content: @Composable (PaddingValues) -> Unit,

    ) {
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val isSelected = remember { mutableStateOf(Screen.DrawerScreens.Home.title) }

    fun onItemClicked(item: Screen.DrawerScreens) {
        isSelected.value = item.title
        navHostController.navigate(item.droute)
        scope.launch {
            drawerState.close()
        }
    }

    fun selected(item: Screen.DrawerScreens): Boolean {
        return isSelected.value == item.title
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Hello user", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Account", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Account)
                        ,
                        label = "Account",
                        icon = Icons.Filled.AccountCircle,
                        onItemClicked = {
                            onItemClicked(Screen.DrawerScreens.Account)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Subscription),
                        label = "Subscription",
                        icon = Icons.Filled.AddCircle,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Subscription)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.AddAccount),
                        label = "Add Account",
                        icon = Icons.Filled.Person,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.AddAccount)
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Home", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Home),
                        label = "Home",
                        icon = Icons.Outlined.Home,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Home)
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Settings", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Settings),
                        label = "Settings",
                        icon = Icons.Outlined.Settings,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Settings)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.HelpAndFeedback),
                        label = "Help and feedback",
                        icon = Icons.AutoMirrored.Outlined.ExitToApp,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.HelpAndFeedback)
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState,
    ) {
        content(PaddingValues(0.dp))
    }
}
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDrawerExample2(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    navHostController: NavHostController,
    openDialog: MutableState<Boolean> = mutableStateOf(false)
    ) {
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val homeMVVM= viewModel<HomeMVVM>()
    // val isSelected = remember { mutableStateOf(homeMVVM.title.value) } it wont work with the dialog, only would change onItemClicked this state is not a state of a viewmodel
    var isSelected = homeMVVM.title // already a state of a viewmodel

    fun onItemClicked(item: Screen.DrawerScreens) {
        // isSelected.value = item.title
        if (item.droute == Screen.DrawerScreens.AddAccount.droute) {
            // open dialog
            openDialog.value = true
        }
        else navHostController.navigate(item.droute)
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }

    fun selected(item: Screen.DrawerScreens): Boolean {
        return isSelected.value == item.title
    }
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Hello user", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Account", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Account)
                        ,
                        label = "Account",
                        icon = Screen.DrawerScreens.Account.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = {
                            onItemClicked(Screen.DrawerScreens.Account)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Subscription),
                        label = "Subscription",
                        icon = Screen.DrawerScreens.Subscription.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Subscription)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.AddAccount),
                        label = "Add Account",
                        icon = Screen.DrawerScreens.AddAccount.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.AddAccount)
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Home", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Home),
                        label = "Home",
                        icon = Screen.DrawerScreens.Home.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Home)
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Settings", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.Settings),
                        label = "Settings",
                        icon = Screen.DrawerScreens.Settings.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.Settings)
                        }
                    )
                    DrawerItem(
                        selected = selected(Screen.DrawerScreens.HelpAndFeedback),
                        label = "Help and feedback",
                        icon = Screen.DrawerScreens.HelpAndFeedback.icon?.let { painterResource(id = it) }!!,
                        onItemClicked = { /* Handle click */
                            onItemClicked(Screen.DrawerScreens.HelpAndFeedback)
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }

}

@Composable
fun DrawerItem(
    selected: Boolean,
    label: String,
    icon: Painter,// ImageVector,// ImageVector,
    onItemClicked: () -> Unit,
    badge: String? = null,
    modifier: Modifier = Modifier
) {
    NavigationDrawerItem(
        label = { Text(label) },
        selected = selected,
        icon = { Icon(icon, contentDescription = null) },
        badge = { if (badge != null) Text(badge) }, // Placeholder
        onClick = { onItemClicked() }
    )
}