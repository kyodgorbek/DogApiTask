package com.yodgorbek.dogapitask.core.di

import com.yodgorbek.dogapitask.doglist.domain.usecases.FetchAllBreedsUseCase
import com.yodgorbek.dogapitask.doglist.domain.usecases.FetchAllDogsUseCase
import com.yodgorbek.dogapitask.doglist.domain.usecases.ProdFetchAllBreedsUseCase
import com.yodgorbek.dogapitask.doglist.domain.usecases.ProdFetchAllDogsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module is responsible for defining the creation of any use case dependencies in the application.
 *
 * NOTE: If this gets very large, we may want to consider refactoring and making modules feature
 * dependent.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetAllDogsUseCase(
        getAllTasksUseCase: ProdFetchAllDogsUseCase,
    ): FetchAllDogsUseCase

    @Binds
    abstract fun bindGetAllBreedsUseCase(
        getAllTasksUseCase: ProdFetchAllBreedsUseCase,
    ): FetchAllBreedsUseCase
}
