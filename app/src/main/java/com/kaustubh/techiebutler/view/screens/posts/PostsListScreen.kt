package com.kaustubh.techiebutler.view.screens.posts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.components.AppLoadingScreen
import com.kaustubh.techiebutler.viewmodel.PostsListViewModel

@Composable
fun PostsListScreen(
    viewModel: PostsListViewModel = hiltViewModel(),
    onItemClicked: (TypeCodeItem) -> Unit
) {
    val items: LazyPagingItems<TypeCodeItem> = viewModel.postsList.collectAsLazyPagingItems()

    var listItems by remember { mutableStateOf<LazyPagingItems<TypeCodeItem>?>(null) }
    LaunchedEffect(key1 = items) {
        listItems = items
    }
    PostsListView(listItems) {
        onItemClicked(it)
    }
}

@Composable
fun PostsListView(
    pagination: LazyPagingItems<TypeCodeItem>?,
    onItemClicked: (TypeCodeItem) -> Unit
) {
    pagination ?: return

    val listState: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            count = pagination.itemCount,
            key = { pagination.peek(it)?.id ?: 0 },
        ) { index ->
            val item = pagination[index]
            if (item != null) {
                PostCard(item = item) {
                    onItemClicked(item)
                }
            }
        }

        if (pagination.loadState.refresh == LoadState.Loading) {
            for (count in 0..3) {
                item {
                    PostCardShimmer()
                }
            }
        }

        if (pagination.loadState.append == LoadState.Loading) {
            item {
                AppLoadingScreen()
            }
        }
    }
}
