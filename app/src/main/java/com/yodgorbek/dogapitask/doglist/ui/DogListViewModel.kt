package com.yodgorbek.dogapitask.doglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.dogapitask.data.Result
import com.yodgorbek.dogapitask.doglist.domain.model.Breed
import com.yodgorbek.dogapitask.doglist.domain.model.Dog
import com.yodgorbek.dogapitask.doglist.domain.usecases.FetchAllBreedsUseCase
import com.yodgorbek.dogapitask.doglist.domain.usecases.FetchAllDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class is responsible for managing and exposing the UI state of the dog list screen.
 */
@HiltViewModel
class DogListViewModel @Inject constructor(
    private val fetchAllBreedsUseCase: FetchAllBreedsUseCase,
    private val fetchAllDogsUseCase: FetchAllDogsUseCase,
) : ViewModel() {

    private val _viewStateDogResult: MutableStateFlow<List<Dog>> =
        MutableStateFlow(emptyList())
    val viewStateDogResult: StateFlow<List<Dog>> = _viewStateDogResult

    private val _viewStateBreedResult: MutableStateFlow<List<Breed>> =
        MutableStateFlow(emptyList())
    val viewStateBreedResult: StateFlow<List<Breed>> = _viewStateBreedResult

    private val _viewStateError: MutableStateFlow<String> =
        MutableStateFlow("")
    val viewStateError: StateFlow<String> = _viewStateError

    private val _viewStateLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val viewStateIsLoading: StateFlow<Boolean> = _viewStateLoading

    init {
        fetchAllBreeds()
    }

    fun fetchAllBreeds() {
        startLoading()
        viewModelScope.launch {
            val breedResult = fetchAllBreedsUseCase()
            stopLoading()

            when (breedResult) {
                is Result.Success -> {
                    _viewStateBreedResult.value = breedResult.data
                    fetchAllDogByBreedId(breedResult.data.first().id)
                }
                is Result.Error -> {
                    _viewStateError.value = breedResult.error.localizedMessage ?: "Unknown error"
                }
            }
        }
    }

    fun fetchAllDogByBreedId(breedId: String) {
        startLoading()
        viewModelScope.launch {
            val dogResult = fetchAllDogsUseCase(breedId)
            stopLoading()

            when (dogResult) {
                is Result.Success -> {
                    _viewStateDogResult.value = dogResult.data
                }
                is Result.Error -> {
                    _viewStateError.value = dogResult.error.localizedMessage ?: "Unknown error"
                }
            }
        }
    }

    private fun stopLoading() {
        _viewStateLoading.value = false
    }

    private fun startLoading() {
        _viewStateLoading.value = true
    }
}
