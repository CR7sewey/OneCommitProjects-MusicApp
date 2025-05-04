package com.mike.musicapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
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
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.mike.musicapp.common.modules.bottomModalItems

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val navHostController = rememberNavController()
                val scaffoldState: ScaffoldState = rememberScaffoldState()
                val scope: CoroutineScope = rememberCoroutineScope()
                val bottomSheetScaffoldState = rememberModalBottomSheetState(
                    //intialValue = ModalBottomSheetValue.Hidden,
                    skipPartiallyExpanded = false,
                    confirmValueChange = { newState ->
                        //newState != ModalBottomSheetValue.HalfExpanded
                        true
                    }
                )
                val homeMVVM = viewModel<HomeMVVM>()
                var openDialog = remember { mutableStateOf(false) }
                var showBottomSheet by remember { mutableStateOf(false) }

                val showTopBottomDrawerBar = remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        if (showTopBottomDrawerBar.value) {
                            TopBar(
                                scaffoldState,
                                scope,
                                navHostController,
                                homeMVVM.screen.value.title,
                                {
                                    showBottomSheet = !showBottomSheet
                                })
                        }
                    },
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        if (showTopBottomDrawerBar.value) {
                            DetailedDrawerExample2(
                                scaffoldState = scaffoldState,
                                scope = scope,
                                navHostController = navHostController,
                                openDialog = openDialog
                            )
                        }
                    },
                    bottomBar = {
                        if (showTopBottomDrawerBar.value) {
                            BottomNavigationBar(
                                navController = navHostController,
                                modifier = Modifier
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                        Navigation(navHostController,  showTopBottomDrawerBarFunction = {
                            showTopBottomDrawerBar.value = true
                        },innerPadding, modifier = Modifier)
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
                        if (showBottomSheet) {
                            BottomModal(
                                onDismissRequest = {
                                    showBottomSheet = false
                                },
                                bottomSheetScaffoldState = bottomSheetScaffoldState,
                                modifier = Modifier
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
    showBottomSheet: () -> Unit,
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
                        painter = painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = "Profile",
                        tint = colorResource(id = R.color.white)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        showBottomSheet.invoke()
                    },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomModal(onDismissRequest: () -> Unit, bottomSheetScaffoldState: SheetState, modifier: Modifier = Modifier) {

        ModalBottomSheet(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            sheetState = bottomSheetScaffoldState,
            modifier = modifier.fillMaxHeight(0.3f),
        ) {
            // Main content of the screen
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                 {
                bottomModalItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDismissRequest.invoke()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        val icon = item.icon ?: 0
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 36.dp)
                                .size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = item.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }
            }
        }

}
