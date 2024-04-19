package com.kaustubh.techiebutler.model

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable

@Keep
@Immutable
data class TypeCodeItem(
    val id: Long? = null,
    val userId: Long? = null,
    val title: String? = null,
    val body: String? = null,
)