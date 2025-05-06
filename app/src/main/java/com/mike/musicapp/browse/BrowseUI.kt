package com.mike.musicapp.browse

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mike.musicapp.R
import com.mike.musicapp.common.api.IconsDTO
import com.mike.musicapp.common.modules.Category
import com.mike.musicapp.common.modules.Category2
import com.mike.musicapp.common.modules.CategoryDTO
import com.mike.musicapp.common.modules.CategoryItemDTO
import com.mike.musicapp.common.modules.ImageDTO
import com.mike.musicapp.common.modules.Screen

/*
val listScroll = listOf<Category>(
    Category(name = "Hits", imageUrl = com.mike.musicapp.R.drawable.hiphop),
    Category(name = "Jazz", imageUrl = com.mike.musicapp.R.drawable.jazz),
    Category(name = "Rock", imageUrl = com.mike.musicapp.R.drawable.rock),
    Category(name = "Workout", imageUrl = com.mike.musicapp.R.drawable.workout),
    Category(name = "Pop", imageUrl = com.mike.musicapp.R.drawable.hiphop),
    Category(name = "Country", imageUrl = com.mike.musicapp.R.drawable.country),
    Category(name = "R&B", imageUrl = com.mike.musicapp.R.drawable.rb),
    Category(name = "Artists", imageUrl = com.mike.musicapp.R.drawable.artists, categoryRoute = Screen.CategoriesScreen.Artists.croute),

    ).groupBy { it -> it.name[0] }

 */
/*
"Chill",
"Classical",
"Country",
"R&B",
*/


@Composable
fun BrowseUI(texto: String, navHostController: NavHostController, modifier: Modifier = Modifier) {

    val browseMVVM = viewModel<BrowseMVVM>()
    val categories = browseMVVM.categories.collectAsState().value

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
                    /*for (i in categories?.categories?.items ?: emptyList()) {
                        val item = Category2(
                            id = i.id,
                            name = i.name,
                            imageUrl = i.icons[0].url,
                            categoryRoute = Screen.CategoriesScreen.Artists.createRoute(i.id)
                        ) //List<CategoryDTO> = i*/

                    val items2 =
                        categories?.categories?.items?.toMutableList()?.plus(
                            CategoryItemDTO(
                                id = "0",
                                name = "Artists",
                                href = Screen.CategoriesScreen.Artists.croute,
                                icons = listOf(
                                    ImageDTO(
                                        url = R.drawable.artists.toString(),
                                        height = 256,
                                        width = 256
                                    )
                                )

                            )
                        )


                        items(items2?: emptyList()) { index ->
                            val item = Category2(
                                id = index.id,
                                name = index.name,
                                imageUrl = index.icons[0].url,
                                categoryRoute = Screen.CategoriesScreen.Artists.createRoute(index.id)
                            )
                            HomeItem(
                                item = item,
                                modifier = modifier.clickable {
                                    if (index.href.isNotEmpty() && index.name == "Artists") {
                                        navHostController.navigate(index.href)
                                    }
                                }
                            )
                      //  }
                    }

                }

    }


}

@Composable
fun HomeItem(item: Category2, modifier: Modifier = Modifier) {
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
                if (item.name == "Artists") {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(id = item.imageUrl.toInt()),
                        contentDescription = "Album Art",
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = modifier.matchParentSize()
                    )
                }
                /*Image(
                    painter = androidx.compose.ui.res.painterResource(id = item.imageUrl),
                    contentDescription = "Album Art",
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    modifier = modifier.matchParentSize()
                )*/
                else {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = "Album Art",
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = modifier.matchParentSize()
                    )
                }
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
@Composable
fun HomeItem2(item: Category, modifier: Modifier = Modifier) {
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
