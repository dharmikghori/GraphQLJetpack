package com.graphql.task.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.graphql.test.R

@Composable
fun CircularImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = if (imageUrl.isBlank()) {
            painterResource(id = R.drawable.ic_launcher_foreground)
        } else rememberAsyncImagePainter(imageUrl),
        contentDescription = stringResource(id = R.string.profile_image),
        modifier = modifier.clip(CircleShape)

    )
}