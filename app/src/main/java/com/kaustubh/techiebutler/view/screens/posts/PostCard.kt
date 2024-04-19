package com.kaustubh.techiebutler.view.screens.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.screens.comments.CommentsScreen
import com.kaustubh.techiebutler.view.theme.IdBackgroundColor
import com.kaustubh.techiebutler.view.theme.IdTextColor

@Composable
fun PostCard(
    item: TypeCodeItem,
    showComments: Boolean = false,
    onItemClicked: (TypeCodeItem) -> Unit = { }
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
            .clickable {
                onItemClicked(item)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "User ID: ${item.userId}",
                    color = MaterialTheme.colorScheme.onSecondary,
                    maxLines = 1,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${item.id}",
                    color = IdTextColor,
                    maxLines = 1,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            color = IdBackgroundColor,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .padding(horizontal = 8.dp),
                )
            }
            Text(
                text = item.title ?: "",
                color = MaterialTheme.colorScheme.onSecondary,
                maxLines = 2,
                fontSize = 15.sp,
                lineHeight = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                text = item.body ?: "",
                color = MaterialTheme.colorScheme.onSecondary,
                maxLines = 6,
                fontSize = 14.sp,
                lineHeight = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp),
            )

            if (showComments) {
                item.id?.let {
                    CommentsScreen(it)
                }
            }
        }
    }
}
