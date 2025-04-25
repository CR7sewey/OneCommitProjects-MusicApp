package com.mike.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mike.musicapp.common.UI.DetailedDrawerExample2
import com.mike.musicapp.common.UI.DialogUI
import com.mike.musicapp.common.modules.Screen
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

