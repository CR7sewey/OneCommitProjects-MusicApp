package com.mike.musicapp.subscription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mike.musicapp.R

@Composable
fun SubscriptionUI(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth().padding(8.dp)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth().height(140.dp).padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = CardDefaults.shape,
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                hoveredElevation = 8.dp,
                focusedElevation = 8.dp
            ),
        ) {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {


                    Column(
                        modifier = modifier.padding(8.dp),
                    ) {
                        Row {
                            Column {
                                Text(
                                    text = "Musical",
                                    modifier = modifier,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Free Tier",
                                    modifier = modifier
                                )
                            }


                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = modifier.padding(end = 4.dp)
                            ) {
                                Row(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                    Text(
                                        text = "See All Plans",
                                        modifier = modifier.padding(end = 2.dp),
                                        color = com.mike.musicapp.ui.theme.Purple40,
                                    )
                                    Icon(
                                        imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowRight,
                                        contentDescription = "Arrow",
                                        modifier = modifier.padding(2.dp),
                                        tint = com.mike.musicapp.ui.theme.Purple40

                                    )
                                }
                            }
                        }
                        Spacer(modifier = modifier.height(4.dp))
                        HorizontalDivider(
                            modifier = modifier.padding( 4.dp),
                            color = Color.Gray,
                            thickness = 1.dp
                        )
                        Spacer(modifier = modifier.height(4.dp))
                        Row(
                            modifier = modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            val ico = R.drawable.baseline_account_circle_24
                            Icon(
                                painter = painterResource(ico),
                                contentDescription = "Plan Icon",
                                modifier = modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Get a Plan",
                                modifier = modifier
                            )

                        }

                }





            }
        }
       // Spacer(modifier = modifier.height(16.dp))

/*
        Card(
                modifier = modifier
                    .fillMaxWidth().height(70.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp,
                    hoveredElevation = 8.dp,
                    focusedElevation = 8.dp
                ),
            ) {

            Row(
                modifier = modifier.fillMaxSize().padding(start = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                val ico = R.drawable.baseline_account_circle_24
                Icon(
                    painter = painterResource(ico),
                    contentDescription = "Plan Icon",
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Get a Plan",
                    modifier = modifier
                )

            }
            }*/

        }




}