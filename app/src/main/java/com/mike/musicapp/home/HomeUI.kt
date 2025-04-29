package com.mike.musicapp.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mike.musicapp.common.UI.DialogUI
import com.mike.musicapp.common.modules.Category
import com.mike.musicapp.common.modules.Screen

val categories = listOf<String>(
    "New Releases",
    "Favorites",
    "Top Rated",
    "Recommended",
)

val listScroll = listOf<Category>(
    Category(name = "Hits", imageUrl = com.mike.musicapp.R.drawable.hiphop),
    Category(name = "Jazz", imageUrl = com.mike.musicapp.R.drawable.jazz),
    Category(name = "Rock", imageUrl = com.mike.musicapp.R.drawable.rock),
    Category(name = "Workout", imageUrl = com.mike.musicapp.R.drawable.workout),
    Category(name = "Pop", imageUrl = com.mike.musicapp.R.drawable.hiphop),
    Category(name = "Country", imageUrl = com.mike.musicapp.R.drawable.country),
    Category(name = "R&B", imageUrl = com.mike.musicapp.R.drawable.rb),

).groupBy { it -> it.name[0] }
/*
"Chill",
"Classical",
"Country",
"R&B",
*/


@Composable
fun HomeUI(texto: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    var rememberScroll = rememberScrollState(0)

    val homeMVVM = viewModel<HomeMVVM>()
    val cats = homeMVVM.categories.collectAsState().value
    Log.d("TAG", "HomeUI: ${cats.toString()}")

    Column(
        modifier = modifier
            .verticalScroll(rememberScroll)
            .padding(8.dp).fillMaxSize()
    ) {

        for (i in categories) {
            Column {
                Text(
                    text = i,
                    modifier = modifier.padding(start = 8.dp, bottom = 8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyRow {
                    for (i in listScroll) {
                        val key = i.key
                        val item: List<Category> = i.value
                        items(item) { index ->
                            HomeItem(
                                item = index,
                                modifier = modifier
                            )
                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(8.dp))

        }



    }


}

@Composable
fun HomeItem(item: Category, modifier: Modifier = Modifier) {
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
            Image(
                painter = androidx.compose.ui.res.painterResource(id = item.imageUrl),
                contentDescription = "Album Art",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = modifier.matchParentSize()
            )
        }
    }
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = item.name,
            color = androidx.compose.ui.graphics.Color.Black,
            maxLines = 2,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )
    }
}
