package com.yodgorbek.dogapitask.doglist.domain.usecases

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Breed
import com.yodgorbek.dogapitask.doglist.domain.repository.DogListRepository
import javax.inject.Inject

class ProdFetchAllBreedsUseCase @Inject constructor(
    private val dogListRepository: DogListRepository,
) : FetchAllBreedsUseCase {

    override suspend fun invoke(): Result<List<Breed>> {
        return dogListRepository.fetchAllDogsBreeds()
    }
}
