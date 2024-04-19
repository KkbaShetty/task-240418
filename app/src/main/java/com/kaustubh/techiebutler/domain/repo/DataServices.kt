package com.kaustubh.techiebutler.domain.repo

import com.kaustubh.techiebutler.model.TypeCodeItem

interface DataServices {

    suspend fun getAllPosts(start: Int, pageSize: Int): List<TypeCodeItem>?

}