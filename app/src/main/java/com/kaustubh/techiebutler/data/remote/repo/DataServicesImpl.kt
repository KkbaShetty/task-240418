package com.kaustubh.techiebutler.data.remote.repo

import com.kaustubh.techiebutler.data.remote.doApiCall
import com.kaustubh.techiebutler.data.remote.services.ApiServices
import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.domain.repo.DataServices
import com.kaustubh.techiebutler.model.PostComment
import com.kaustubh.techiebutler.model.TypeCodeItem
import javax.inject.Inject

class DataServicesImpl @Inject constructor(
    private val apiService: ApiServices
) : DataServices {

    override suspend fun getAllPosts(start: Int, pageSize: Int): List<TypeCodeItem>? {
        val result = doApiCall {
            apiService.getAllPosts(start, pageSize)
        }
        return if (result is DataResult.Success) result.data else null
    }

    override suspend fun fetchCommentsForPost(postId: Long): DataResult<List<PostComment>, DataError> {
        return doApiCall {
            apiService.getCommentsForPost(postId)
        }
    }
}