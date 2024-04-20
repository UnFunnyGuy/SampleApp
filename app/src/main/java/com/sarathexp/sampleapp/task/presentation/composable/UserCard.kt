package com.sarathexp.sampleapp.task.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sarathexp.sampleapp.task.data.model.User

@Composable
fun UserCard(user: User, onClick: () -> Unit, modifier: Modifier = Modifier) {

    Card(modifier = modifier, onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(user.avatar)
                        .crossfade(true)
                        .build(),
                modifier = Modifier.padding(8.dp).clip(RoundedCornerShape(10.dp)).size(65.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                fallback = painterResource(id = com.sarathexp.sampleapp.task.R.drawable.cloud_off),
                placeholder =
                    painterResource(id = com.sarathexp.sampleapp.task.R.drawable.cloud_off),
                colorFilter =
                    if (user.avatar == null) ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    else null
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = user.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}
