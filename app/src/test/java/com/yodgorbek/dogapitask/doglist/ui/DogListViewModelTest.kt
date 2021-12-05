package com.yodgorbek.dogapitask.doglist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.yodgorbek.dogapitask.CoroutinesTestRule
import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Breed
import com.yodgorbek.dogapitask.doglist.domain.model.Dog
import com.yodgorbek.dogapitask.doglist.domain.usecases.ProdFetchAllBreedsUseCase
import com.yodgorbek.dogapitask.doglist.domain.usecases.ProdFetchAllDogsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DogListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @RelaxedMockK
    lateinit var fetchAllBreedsUseCase: ProdFetchAllBreedsUseCase

    @RelaxedMockK
    lateinit var fetchAllDogsUseCase: ProdFetchAllDogsUseCase

    lateinit var subject: DogListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        subject = DogListViewModel(
            fetchAllBreedsUseCase = fetchAllBreedsUseCase,
            fetchAllDogsUseCase = fetchAllDogsUseCase
        )
    }

    @Test
    fun `given  DogListViewModel , when created , then fetchAllBreeds should be called`() {
        coEvery {
            fetchAllBreedsUseCase()
        }
        coVerify { fetchAllBreedsUseCase() }
    }

    @Test
    fun `given  fetchAllBreeds , when success , then viewStateDogResult should be not empty`() {
        coEvery {
            fetchAllBreedsUseCase()
        } returns
                Result.Success(listOf(Breed("10", "fakeDog")))

        subject.fetchAllBreeds()

        Truth.assertThat(subject.viewStateBreedResult.value).isNotEmpty()
        Truth.assertThat(subject.viewStateIsLoading.value).isEqualTo(false)
    }

    @Test
    fun `given  fetchAllBreeds , when fail , then viewStateError should be not empty`() {
        coEvery {
            fetchAllBreedsUseCase()
        } returns
                Result.Error(Exception("DogError"))

        subject.fetchAllBreeds()

        Truth.assertThat(subject.viewStateError.value).isEqualTo("DogError")
        Truth.assertThat(subject.viewStateIsLoading.value).isEqualTo(false)
    }

    @Test
    fun `given  fetchAllDogsUseCase , when success , then viewStateDogResult should be not empty`() {
        coEvery {
            fetchAllDogsUseCase("dogx")
        } returns
                Result.Success(listOf(Dog("fakeDog")))

        subject.fetchAllDogByBreedId("dogx")

        Truth.assertThat(subject.viewStateDogResult.value).isNotEmpty()
        Truth.assertThat(subject.viewStateIsLoading.value).isEqualTo(false)
    }

    @Test
    fun `given  fetchAllDogsUseCase , when fail , then viewStateError should be not empty`() {
        coEvery {
            fetchAllDogsUseCase("dogx")
        } returns
                Result.Error(Exception("DogError"))

        subject.fetchAllDogByBreedId("dogx")

        Truth.assertThat(subject.viewStateError.value).isEqualTo("DogError")
        Truth.assertThat(subject.viewStateIsLoading.value).isEqualTo(false)
    }
}
