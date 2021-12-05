package com.yodgorbek.dogapitask.doglist.domain.usecases

import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Breed

interface FetchAllBreedsUseCase {
    suspend operator fun invoke(): Result<List<Breed>>
}
