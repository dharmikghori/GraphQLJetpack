package com.graphql.task.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.graphql.test.R

class AppImages {
    val post: Painter
        @Composable
        get() = painterResource(id = R.drawable.post_add)

    val deleteUser: Painter
        @Composable
        get() = painterResource(id = R.drawable.delete)

    val backArrow: Painter
        @Composable
        get() = painterResource(id = R.drawable.baseline_keyboard_backspace_24)
}

