package com.kaustubh.techiebutler.view.screens.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.model.PostComment
import com.kaustubh.techiebutler.view.components.AppLoadingScreen
import com.kaustubh.techiebutler.viewmodel.PostsViewModel

@Composable
fun CommentsScreen(
    postId: Long,
    postsViewModel: PostsViewModel = hiltViewModel()
) {
    val currentList = postsViewModel.commentsList.collectAsStateWithLifecycle()
    if (currentList.value == null) {
        AppLoadingScreen()
        postsViewModel.fetchCommentsForPost(postId)
        return
    }

    currentList.value?.let {
        if (currentList.value is DataResult.Success) {
            val data = (currentList.value as? DataResult.Success)?.data
            data?.let {
                DisplayComments(it)
            }
        }
    }
}

@Composable
fun DisplayComments(comments: List<PostComment>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Comments:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp, top = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
        )
        DisplayCommentsList(comments)
    }
}

@Composable
fun DisplayCommentsList(commentsList: List<PostComment>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = commentsList,
            key = {
                it.id ?: 0L
            }
        ) { item ->
            CommentCard(item)
        }
    }
}
