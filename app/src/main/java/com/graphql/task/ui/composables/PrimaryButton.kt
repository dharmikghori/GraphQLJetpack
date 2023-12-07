package com.graphql.task.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.graphql.task.theme.AppTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Text(
        text = text,
        style = AppTheme.typography.semiBoldMontserrat16.copy(textAlign = TextAlign.Center),
        color = AppTheme.colors.white,
        modifier = modifier
            .alpha(if (!enabled) 0.5f else 1f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(AppTheme.colors.fillPrimary)
            .padding(horizontal = 12.dp, vertical = 9.dp)
            .padding(top = 1.dp),
    )
}