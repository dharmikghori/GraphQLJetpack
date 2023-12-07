package com.graphql.task.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.graphql.task.theme.AppTheme

@Composable
fun PrimaryText(
    text: String,
    style: TextStyle,
    color: Color = AppTheme.colors.black,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = style,
        modifier = modifier,
        color = color
    )
}