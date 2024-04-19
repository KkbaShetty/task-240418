package com.kaustubh.techiebutler.domain.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaustubh.techiebutler.data.remote.NetworkConstants.PAGE_SIZE
import com.kaustubh.techiebutler.domain.repo.DataServices
import com.kaustubh.techiebutler.model.TypeCodeItem
import retrofit2.HttpException
import java.io.IOException

class TypeCodePostsSource(
    private val dataServices: DataServices
) : PagingSource<Int, TypeCodeItem>() {
    override fun getRefreshKey(state: PagingState<Int, TypeCodeItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TypeCodeItem> {
        return try {
            val nextPage = params.key ?: 1
            val startIndex = (nextPage - 1) * PAGE_SIZE
            val result = dataServices.getAllPosts(startIndex, PAGE_SIZE)
            LoadResult.Page(
                data = result ?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (result.isNullOrEmpty()) null else nextPage + 1
            )
        } catch (ex: IOException) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }
}