package com.mike.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mike.musicapp.common.UI.DetailedDrawerExample2
import com.mike.musicapp.common.UI.DialogUI
import com.mike.musicapp.common.modules.Screen
import com.mike.musicapp.common.modules.bottomNavIcons
import com.mike.musicapp.common.modules.screens
import com.mike.musicapp.home.HomeMVVM
import com.mike.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val navHostController = rememberNavController()
                val scaffoldState: ScaffoldState = rememberScaffoldState()
                val scope: CoroutineScope = rememberCoroutineScope()
                val homeMVVM = viewModel<HomeMVVM>()
                var openDialog = remember { mutableStateOf(false) }

                Scaffold(
                    topBar = { TopBar(scaffoldState, scope, navHostController, homeMVVM.screen.value.title) },
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        DetailedDrawerExample2(
                            scaffoldState = scaffoldState,
                            scope = scope,
                            navHostController = navHostController,
                            openDialog = openDialog
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navHostController,
                            modifier = Modifier
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Navigation(navHostController, innerPadding, modifier = Modifier)
                    if (openDialog.value) {
                        DialogUI(
                            onDismissRequest = {
                                openDialog.value = false
                                navHostController.navigate(Screen.DrawerScreens.Home.droute)
                            },
                            onConfirm = { emailAddress, password ->
                                Log.d("HomeUI", "Email: $emailAddress, Password: $password")
                                openDialog.value = false
                                navHostController.navigate(Screen.DrawerScreens.Home.droute)
                            },
                            dialogOpen = openDialog
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    navHostController: NavHostController,
    title: String = "Home",
    modifier: Modifier = Modifier) {

        //val route by remember { mutableStateOf(navHostController.graph.route) }
        fun getTitle(route: String?): String {
            val s = screens.find { it.route == route }
            return s?.title ?: "title"

        }

        TopAppBar(
            title = {
                Text(
                    text = title,
                    modifier = Modifier
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            if (scaffoldState.drawerState.isClosed) {
                                scaffoldState.drawerState.open()
                               // drawerState.open()
                            } else {
                                scaffoldState.drawerState.close()
                                //drawerState.close()

                            }

                        }


                    }, // courotine method, suspend function
                    modifier = Modifier.padding(start = 4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = colorResource(id = R.color.white)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.padding(end = 4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = colorResource(id = R.color.white)
                    )
                }

            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            modifier = modifier
        )

}

@Composable
fun BottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val HomeMVVM = viewModel<HomeMVVM>()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 20.dp,
    ) {
        bottomNavIcons.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.route == HomeMVVM.screen.value.route, //selectedNavigationIndex.intValue == index,
                onClick = {
                    //selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    if (item.icon == null) {
                        return@NavigationBarItem
                    }
                    Icon(
                        painter = painterResource(id = item.icon),
                        modifier = modifier.size(24.dp),

                        contentDescription = null,
                        tint = if (item.route == HomeMVVM.screen.value.route)
                            Color.White
                        else Color.Gray
                    )
                    //Icon(painter = painterResource(id = item.icon), contentDescription = null)

                },
                label = {
                    Text(
                        item.title,
                        color = if (item.route == HomeMVVM.screen.value.route)
                            Color.White
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}


