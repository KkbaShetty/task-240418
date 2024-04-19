package com.kaustubh.techiebutler.view.screens.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.screens.comments.CommentsScreen
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

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
            PostCardContent(item)

            if (showComments) {
                item.id?.let {
                    CommentsScreen(it)
                }
            }
        }
    }
}

@Composable
fun PostCardShimmer() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 12.dp)
            .shimmer(shimmerInstance),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {

    }
}
