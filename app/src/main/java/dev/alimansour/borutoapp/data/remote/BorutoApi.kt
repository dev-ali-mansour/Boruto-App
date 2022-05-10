package dev.alimansour.borutoapp.data.remote

import dev.alimansour.borutoapp.data.remote.response.ApiResponse
import dev.alimansour.borutoapp.util.Constants.ITEMS_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = ITEMS_PER_PAGE
    ): ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse


}