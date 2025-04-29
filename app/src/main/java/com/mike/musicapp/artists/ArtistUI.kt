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

@Composable
fun ArtistUI(id: String, artistsMVVM: ArtistsMVVM, navHostController: NavHostController, modifier: Modifier = Modifier) {

    artistsMVVM.getArtistById(id)
    artistsMVVM.getArtistAlbums(id)
    val artist = artistsMVVM.individualArtist.collectAsState().value
    val artistAlbums = artistsMVVM.artistAlbums.collectAsState().value

    if (artist != null) {
        ArtistScreen(
            artist = artist,
            artistAlbums = artistAlbums,

            modifier = modifier
        )
    } else {
        // Handle loading state or error state here
    }

}

@Composable
fun ArtistScreen(artist: ArtistDTO, artistAlbums: ArtistAlbumsDTO?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
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
                modifier = modifier.fillMaxWidth()
                    .height(360.dp)
            )
            // Display artist name
            Text(
                text = artist.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier.padding(8.dp)
                    .align(Alignment.BottomStart),
                    //.background(Color.Black.copy(alpha = 0.5f)),
                color = Color.White,
                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,

                )
        }
        Spacer(modifier = modifier.height(12.dp))

        LazyRow {

            items(artistAlbums?.items ?: emptyList()) { album ->
                Column(
                    modifier = modifier
                        .padding(start = 8.dp, bottom = 12.dp, end = 8.dp)
                        .width(150.dp)
                        .height(180.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = modifier
                            .padding(0.dp).width(150.dp).height(150.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            AsyncImage(
                                model = album.images[0].url,
                                contentDescription = "Album Art",
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                modifier = modifier.fillMaxWidth()
                                    .matchParentSize()
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(4.dp))
                    Text(
                        text = album.name,
                        color = androidx.compose.ui.graphics.Color.Black,
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    )
                }
            }
        }

    }

}