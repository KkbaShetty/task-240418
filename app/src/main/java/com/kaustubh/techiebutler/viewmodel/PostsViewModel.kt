package com.kaustubh.techiebutler.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.domain.repo.DataServices
import com.kaustubh.techiebutler.model.PostComment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val dataService: DataServices
) : ViewModel() {

    private val _commentsList = MutableStateFlow<DataResult<List<PostComment>, DataError>?>(null)
    val commentsList: StateFlow<DataResult<List<PostComment>, DataError>?> = _commentsList.asStateFlow()
    fun fetchCommentsForPost(postId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = dataService.fetchCommentsForPost(postId)) {
                is DataResult.Loading -> _commentsList.emit(DataResult.Loading())
                is DataResult.Error -> _commentsList.emit(DataResult.Error(result.error))
                is DataResult.Success -> _commentsList.emit(DataResult.Success(result.data))
            }
        }
    }
}