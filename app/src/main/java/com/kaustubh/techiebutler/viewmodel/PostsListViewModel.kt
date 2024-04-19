package com.kaustubh.techiebutler.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaustubh.techiebutler.data.NetworkConstants.PAGE_SIZE
import com.kaustubh.techiebutler.domain.page.TypeCodePostsSource
import com.kaustubh.techiebutler.domain.repo.DataServices
import com.kaustubh.techiebutler.model.TypeCodeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val dataServices: DataServices
) : ViewModel() {
    val postsList: Flow<PagingData<TypeCodeItem>> = Pager(PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = 2,
        enablePlaceholders = true,
    )) {
        TypeCodePostsSource(dataServices)
    }.flow.cachedIn(viewModelScope)
}
