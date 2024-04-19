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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.theme.CardBackgroundColor
import com.kaustubh.techiebutler.view.theme.IdBackgroundColor
import com.kaustubh.techiebutler.view.theme.IdTextColor

@Composable
fun PostCard(
    item: TypeCodeItem,
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
            containerColor = CardBackgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "User ID: ${item.userId}",
                    color = Color.Black,
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
                        .background(color = IdBackgroundColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp),
                )
            }
            Text(
                text = item.title ?: "",
                color = Color.Black,
                maxLines = 2,
                fontSize = 15.sp,
                lineHeight = 18.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                text = item.body ?: "",
                color = Color.Black,
                maxLines = 6,
                fontSize = 14.sp,
                lineHeight = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            )
        }
    }
}
