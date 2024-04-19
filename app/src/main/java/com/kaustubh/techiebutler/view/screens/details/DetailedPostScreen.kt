package com.kaustubh.techiebutler.view.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.components.AppChildScreen
import com.kaustubh.techiebutler.view.screens.posts.PostCard

@Composable
fun DetailedPostScreen(post: TypeCodeItem) {
    AppChildScreen("Post Details") { padding ->
        DetailedPost(padding, post)
    }
}

@Composable
fun DetailedPost(
    innerPadding: PaddingValues,
    post: TypeCodeItem,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
    ) {
        PostCard(post, true)
    }
}
