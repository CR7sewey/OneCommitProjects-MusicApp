package com.mike.musicapp.artists

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mike.musicapp.browse.HomeItem
//import com.mike.musicapp.browse.listScroll
import com.mike.musicapp.common.modules.ArtistDTO
import com.mike.musicapp.common.modules.ArtistsDTO
import com.mike.musicapp.common.modules.Category
import com.mike.musicapp.common.modules.Screen
import kotlin.collections.iterator

@Composable
fun ArtistsUI(nvaHostController: NavHostController, artistsMVVM: ArtistsMVVM, modifier: Modifier = Modifier) {


    val currentArtists = artistsMVVM.artists.collectAsState().value
    Log.d("ArtistsUI", "Current Artists: ${currentArtists.toString()}")
    ArtistsUIContent(
        currentArtists = currentArtists,
        navHostController = nvaHostController,
        modifier = modifier
    )

}

@Composable
fun ArtistsUIContent(currentArtists: ArtistsDTO?, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp).fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            currentArtists?.artists?.let { it ->
                items(it) { index ->
                    HomeItem(
                        item = index,
                        modifier = modifier.clickable {
                            Log.d("ArtistsUI", "Item clicked: ${index.name}")
                            // Handle item click
                            navHostController.navigate(
                                //Screen.CategoriesScreen.Artists.croute + "/${index.id}"
                                Screen.CategoriesScreen.Artists.createRoute(index.id)
                            )
                        }
                    )
                }
            }
        }

    }
}


@Composable
fun HomeItem(item: ArtistDTO, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
            .width(150.dp)
            .height(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .width(150.dp).height(150.dp),
            border = _root_ide_package_.androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = androidx.compose.ui.graphics.Color.DarkGray
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = item.images[0].url,
                    contentDescription = "Album Art",
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    modifier = modifier.matchParentSize()
                )
            }
        }
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = item.name,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
