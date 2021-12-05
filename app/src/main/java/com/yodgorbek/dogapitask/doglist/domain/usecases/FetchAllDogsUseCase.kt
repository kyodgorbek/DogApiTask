package com.yodgorbek.dogapitask.doglist.domain.usecases

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Dog

interface FetchAllDogsUseCase {
    suspend operator fun invoke(breed: String): Result<List<Dog>>
}
