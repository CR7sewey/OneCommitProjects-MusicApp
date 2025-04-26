package com.mike.musicapp.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mike.musicapp.R
import com.mike.musicapp.common.modules.Icons

@Composable
fun AccountUI(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth().padding(16.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

                Row(


                ) {
                    val ico = R.drawable.baseline_account_circle_24
                    Icon(
                        painter = painterResource(ico),
                        contentDescription = "Account Icon",
                        modifier = modifier.padding(end = 8.dp)
                    )
                    Column {
                        Text(
                            text = "AccountUI",
                            modifier = modifier
                        )
                        Text(
                            text = "username",
                            modifier = modifier
                        )
                    }
                }



            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier.padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    modifier = modifier.padding(8.dp)
                )
            }

            }

        Spacer(modifier = modifier.height(16.dp))

        Row(
            modifier = modifier.fillMaxWidth().padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Row(


            ) {

                val ico = R.drawable.baseline_library_music_24
                Icon(
                    painter = painterResource(ico),
                    contentDescription = "Music Icon",
                    modifier = modifier.padding(end = 8.dp)
                )
                    Text(
                        text = "My Music",
                        modifier = modifier
                    )

            }


            }

    }


}