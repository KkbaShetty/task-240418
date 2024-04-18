package com.kaustubh.techiebutler.domain.repo

import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.model.TypeCodeItem

interface DataServices {

    suspend fun getAllPosts(): DataResult<List<TypeCodeItem>, DataError>

}