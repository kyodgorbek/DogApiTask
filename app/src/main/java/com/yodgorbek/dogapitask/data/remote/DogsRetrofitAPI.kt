package com.yodgorbek.dogapitask.data.remote

import com.yodgorbek.dogapitask.data.models.BreedResult
import com.yodgorbek.dogapitask.data.models.DogsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface DogsRetrofitAPI {

    @GET("v1/breeds")
    suspend fun getDogBreedList(): List<BreedResult>

    @GET("v1/images/search")
    suspend fun getDogListByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 102
    ): List<DogsResult>
}
