package com.yodgorbek.dogapitask.doglist.domain.usecases

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Dog
import com.yodgorbek.dogapitask.doglist.domain.repository.DogListRepository
import javax.inject.Inject

class ProdFetchAllDogsUseCase @Inject constructor(
    private val dogListRepository: DogListRepository,
) : FetchAllDogsUseCase {

    override suspend fun invoke(breed: String): Result<List<Dog>> {
        return dogListRepository.fetchAllDogsByBreed(breed)
    }
}
