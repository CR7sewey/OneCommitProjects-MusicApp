package com.mike.musicapp.library

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mike.musicapp.common.modules.LibraryItems
import com.mike.musicapp.common.modules.libraryItems

@Composable
fun LibraryUI(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(libraryItems) { index ->
                LibraryItem(
                    item = index,
                    modifier = modifier,
                )
            }
        }

    }
    
}

@Composable
fun LibraryItem(
    item: LibraryItems,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            hoveredElevation = 8.dp,
        ),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
                modifier = modifier
                    .fillMaxSize().padding(horizontal = 10.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(item.imageUrl),
                    contentDescription = "Plan Icon",
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = item.name,
                    modifier = modifier
                )
            }
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow",
                modifier = modifier.padding(2.dp),
                tint = com.mike.musicapp.ui.theme.Purple40

            )

        }

    }

}