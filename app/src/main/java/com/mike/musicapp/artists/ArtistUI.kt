package com.mike.musicapp.artists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mike.musicapp.common.modules.ArtistAlbumsDTO
import com.mike.musicapp.common.modules.ArtistDTO
import com.mike.musicapp.common.modules.ArtistTopTracksDTO

@Composable
fun ArtistUI(id: String, artistsMVVM: ArtistsMVVM, navHostController: NavHostController, modifier: Modifier = Modifier) {

    artistsMVVM.getArtistById(id)
    artistsMVVM.getArtistAlbums(id)
    artistsMVVM.getArtistTopTracks(id)
    val artist = artistsMVVM.individualArtist.collectAsState().value
    val artistAlbums = artistsMVVM.artistAlbums.collectAsState().value
    val artistTopTracks = artistsMVVM.artistTopTracks.collectAsState().value

    if (artist != null) {
        ArtistScreen(
            artist = artist,
            artistAlbums = artistAlbums,
            artistTopTracks = artistTopTracks,
            modifier = modifier
        )
    } else {
        // Handle loading state or error state here
    }

}

@Composable
fun ArtistScreen(artist: ArtistDTO, artistAlbums: ArtistAlbumsDTO?, artistTopTracks: ArtistTopTracksDTO?, modifier: Modifier = Modifier) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                // Handle item click
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)

        ) {
            AsyncImage(
                model = artist.images[0].url,
                contentDescription = "Album Art",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(360.dp)
            )
            // Display artist name
            Text(
                text = artist.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
                    //.background(Color.Black.copy(alpha = 0.5f)),
                color = Color.White,
                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,

                )
        }
        Spacer(modifier = modifier.height(12.dp))
        val labels = listOf("Albums", "Top Tracks", "Related Artists")
        Column(
            modifier = modifier
                .fillMaxWidth().verticalScroll(scroll),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            ArtistAlbumsDisplay(artistAlbums = artistAlbums)
            TracksAlbumsDisplay(artistAlbums = artistTopTracks)

        }

    }

}

@Composable
fun ArtistAlbumsDisplay(artistAlbums: ArtistAlbumsDTO?, modifier: Modifier = Modifier) {
    Text(
        text = "Albums",
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.padding(8.dp),
        color = Color.Black,
        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
    )
    LazyRow {

        items(artistAlbums?.items?.size ?: 0 ) { index ->
            val album = artistAlbums?.items?.get(index)
            Column(
                modifier = modifier
                    .padding(start = 8.dp, bottom = 12.dp, end = 8.dp)
                    .width(150.dp)
                    .height(180.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {


                Spacer(modifier = modifier.height(4.dp))
                Card(
                    modifier = modifier
                        .padding(0.dp)
                        .width(150.dp)
                        .height(150.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        AsyncImage(
                            model = album?.images[0]?.url,
                            contentDescription = "Album Art",
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = modifier
                                .fillMaxWidth()
                                .matchParentSize()
                        )
                    }
                }
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = album?.name ?: "Unknown Album",
                    color = androidx.compose.ui.graphics.Color.Black,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun TracksAlbumsDisplay(artistAlbums: ArtistTopTracksDTO?, modifier: Modifier = Modifier) {
    Text(
        text = "Top Tracks",
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.padding(8.dp),
        color = Color.Black,
        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
    )
    LazyRow {

        items(artistAlbums?.tracks?.size ?: 0 ) { index ->
            val album = artistAlbums?.tracks?.get(index)
            Column(
                modifier = modifier
                    .padding(start = 8.dp, bottom = 12.dp, end = 8.dp)
                    .width(150.dp)
                    .height(180.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {


                Spacer(modifier = modifier.height(4.dp))
                Card(
                    modifier = modifier
                        .padding(0.dp)
                        .width(150.dp)
                        .height(150.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        AsyncImage(
                            model = album?.album?.images[0]?.url,
                            contentDescription = "Album Art",
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = modifier
                                .fillMaxWidth()
                                .matchParentSize()
                        )
                    }
                }
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = album?.name ?: "Unknown Album",
                    color = androidx.compose.ui.graphics.Color.Black,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                )
            }
        }
    }
}