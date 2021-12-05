package com.yodgorbek.dogapitask.data.repo

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.data.models.DogsResult
import com.yodgorbek.dogapitask.data.models.mapToDomain
import com.yodgorbek.dogapitask.data.remote.DogsRetrofitAPI
import com.yodgorbek.dogapitask.doglist.domain.repository.DogListRepository
import javax.inject.Inject

/**
 * there is a bug on v1/breeds , it return the wrong breeds ids
 * instead of rblu, ycho, abys it return  1,2,3
 * calling v1/images/search with the wrong ids return single dog
 * here we repeat the call [fetchAllDogsByBreed] 15 until they fixed
 * when they fixed the ids bug you can safely remove the repeat
 * keep track https://api.TheDogAPI.com/v1/images/search?breed_ids=beng
 */
class DefaultDogListRepository @Inject constructor(val dogsRetrofitAPI: DogsRetrofitAPI) :
    DogListRepository {

    override suspend fun fetchAllDogsByBreed(breed: String) =
        runCatching {
            val list = mutableListOf<DogsResult>()
            repeat(15) {
                list += dogsRetrofitAPI.getDogListByBreed(breed)
            }
            list.map { it.mapToDomain() }
        }.fold(
            onSuccess = {
                Result.Success(it)
            },
            onFailure = {
                Result.Error(it)
            }
        )

    override suspend fun fetchAllDogsBreeds() =
        runCatching {
            dogsRetrofitAPI.getDogBreedList().map { it.mapToDomain() }
        }.fold(
            onSuccess = {
                Result.Success(it)
            },
            onFailure = {
                Result.Error(it)
            }
        )
}
