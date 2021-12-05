package com.yodgorbek.dogapitask.doglist.domain.repository

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Breed
import com.yodgorbek.dogapitask.doglist.domain.model.Dog

interface DogListRepository {

    suspend fun fetchAllDogsByBreed(breed: String): Result<List<Dog>>

    suspend fun fetchAllDogsBreeds(): Result<List<Breed>>
}
