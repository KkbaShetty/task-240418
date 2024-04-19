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
import com.kaustubh.techiebutler.navigation.AppNavViewModel
import com.kaustubh.techiebutler.view.components.AppLoadingScreen
import com.kaustubh.techiebutler.viewmodel.PostsListViewModel

@Composable
fun PostsListScreen(
    viewModel: PostsListViewModel = hiltViewModel(),
    navViewModel: AppNavViewModel = hiltViewModel(),
) {
    val items: LazyPagingItems<TypeCodeItem> = viewModel.postsList.collectAsLazyPagingItems()

    var listItems by remember { mutableStateOf<LazyPagingItems<TypeCodeItem>?>(null) }
    LaunchedEffect(key1 = items) {
        listItems = items
    }

    if (listItems == null || listItems?.itemCount == 0) {
        AppLoadingScreen()
    } else {
        PostsListView(listItems) {
            navViewModel.navigateToScreenDetailsPage(it)
        }
    }
}

@Composable
fun PostsListView(
    items: LazyPagingItems<TypeCodeItem>?,
    onItemClicked: (TypeCodeItem) -> Unit
) {
    items ?: return

    val listState: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            count = items.itemCount,
            key = { items.peek(it)?.id ?: 0 },
        ) { index ->
            val item = items[index]
            if (item != null) {
                PostCard(item = item) {
                    onItemClicked(item)
                }
            }
        }
    }

    if (items.loadState.append == LoadState.Loading) {
        AppLoadingScreen()
    }
}
