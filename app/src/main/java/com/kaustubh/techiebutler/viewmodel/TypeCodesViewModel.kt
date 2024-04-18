package com.kaustubh.techiebutler.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.domain.repo.DataServices
import com.kaustubh.techiebutler.model.TypeCodeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeCodesViewModel @Inject constructor(
    private val dataServices: DataServices
) : ViewModel() {

    private val _dataStatePosts = MutableStateFlow<DataResult<List<TypeCodeItem>, DataError>?>(null)
    val dataStatePosts = _dataStatePosts.asStateFlow()
    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataStatePosts.emit(DataResult.Loading())
            when (val result = dataServices.getAllPosts()) {
                is DataResult.Error -> _dataStatePosts.emit(DataResult.Error(result.error))
                is DataResult.Success -> _dataStatePosts.emit(DataResult.Success(result.data))
                else -> {}
            }
        }
    }

}

enum class UiStateHome {
    LOADING, DATA_LIST, ERROR
}

