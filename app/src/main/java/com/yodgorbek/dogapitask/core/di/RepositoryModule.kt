package com.yodgorbek.dogapitask.core.di

import com.yodgorbek.dogapitask.data.repo.DefaultDogListRepository
import com.yodgorbek.dogapitask.doglist.domain.repository.DogListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module is responsible for defining the creation of any repository dependencies used in the
 * application.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskListRepository(
        taskListRepository: DefaultDogListRepository,
    ): DogListRepository
}
