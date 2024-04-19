package com.kaustubh.techiebutler.model

import androidx.annotation.Keep

@Keep
data class PostComment(
    val id: Long?,
    val postId: Long?,
    val name: String?,
    val email: String?,
    val body: String?,
)
