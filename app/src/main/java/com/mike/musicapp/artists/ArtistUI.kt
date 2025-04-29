package com.mike.musicapp.artists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mike.musicapp.common.modules.ArtistDTO

@Composable
fun ArtistUI(id: String, artistsMVVM: ArtistsMVVM, navHostController: NavHostController, modifier: Modifier = Modifier) {

    artistsMVVM.getArtistById(id)
    val artist = artistsMVVM.individualArtist.collectAsState().value

    if (artist != null) {
        ArtistScreen(
            artist = artist,
            modifier = modifier.clickable {
                navHostController.navigateUp()
            }
        )
    } else {
        // Handle loading state or error state here
    }

}

@Composable
fun ArtistScreen(artist: ArtistDTO, modifier: Modifier = Modifier) {
    Column {

            AsyncImage(
                model = artist.images[0].url,
                contentDescription = "Album Art",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
        )
    }
}