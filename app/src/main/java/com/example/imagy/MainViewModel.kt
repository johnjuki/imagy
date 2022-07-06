package com.example.imagy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagy.network.UnsplashApiService
import com.example.imagy.repository.UnsplashApiRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _status: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val status: LiveData<String> = _status

    private val unsplashApiService = UnsplashApiService.instance
    private val unsplashApiRepo = UnsplashApiRepo(unsplashApiService)

    init {
        getUnsplashPhotos()
    }

    private fun getUnsplashPhotos() {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            _status.value = "Failure: ${exception.message}"
        }

        viewModelScope.launch(errorHandler) {
            val photosResult = unsplashApiRepo.editorialFeedPhotos()
            _status.value = "Success: ${photosResult.size} photos received"
        }

    }
}
