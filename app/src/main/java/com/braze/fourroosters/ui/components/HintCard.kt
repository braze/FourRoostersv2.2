package com.braze.fourroosters.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.braze.fourroosters.ui.theme.Teal200
import com.braze.fourroosters.ui.theme.lightGray


@Composable
fun HintCard(index: Int, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        backgroundColor = lightGray
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Teal200),
                contentAlignment = Alignment.Center
            ) {

                //number of attempts
                Text(
                    text = "$index",
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = description.take(4) + ":  " + description.drop(4),
                style = MaterialTheme.typography.h6
            )
        }
    }
}