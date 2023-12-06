package com.graphql.task.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Row(Modifier.align(Alignment.CenterStart)) {

            Spacer(modifier = Modifier.width(12.dp))
            PrimaryText(
                text = user.username ?: "",
                modifier = Modifier.align(Alignment.CenterVertically)
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