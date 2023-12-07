package com.graphql.task.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.graphql.task.theme.AppTheme
import com.graphql.test.UserListQuery

@Composable
fun UserCard(user: UserListQuery.Data1, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { user.username?.let { onClick(it) } }
    ) {
        Row() {
            Column(
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {

                PrimaryText(
                    text = user.username ?: "",
                    style = AppTheme.typography.semiBoldMontserrat16,
                )
                PrimaryText(
                    text = user.email ?: "",
                    style = AppTheme.typography.regularMontserrat14,
                )
            }
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(25.dp),
                painter = AppTheme.images.deleteUser,
                contentDescription = "null"
            )

        }
        Divider(
            Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}