package com.kaustubh.techiebutler.view.screens.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaustubh.techiebutler.model.PostComment

@Composable
fun CommentCard(comment: PostComment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 16.dp)
        ) {
            comment.email?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
            comment.name?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
            comment.body?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }
    }
}
