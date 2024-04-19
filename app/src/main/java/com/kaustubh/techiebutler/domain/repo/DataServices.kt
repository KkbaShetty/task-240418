package com.kaustubh.techiebutler.domain.repo

import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.model.PostComment
import com.kaustubh.techiebutler.model.TypeCodeItem

interface DataServices {

    suspend fun getAllPosts(start: Int, pageSize: Int): List<TypeCodeItem>?

    suspend fun fetchCommentsForPost(postId: Long): DataResult<List<PostComment>, DataError>

}