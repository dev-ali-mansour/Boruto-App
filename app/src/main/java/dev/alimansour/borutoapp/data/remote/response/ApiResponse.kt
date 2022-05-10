package dev.alimansour.borutoapp.data.remote.response

import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<HeroEntity> = emptyList(),
    val lastUpdated: Long? = null
)
