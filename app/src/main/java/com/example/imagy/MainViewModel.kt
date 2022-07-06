package com.example.imagy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagy.network.UnsplashApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException

class MainViewModel : ViewModel() {

    private val _status : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val status: LiveData<String> = _status

    init {
        getUnsplashPhotos()
    }

    private fun getUnsplashPhotos() {
        try {
            viewModelScope.launch {
                val photosResult = UnsplashApi.unsplashService.getPhotos()
                _status.value = "Success: ${photosResult.size} photos received"
            }
        } catch (e: HttpException) {
            _status.value = "Failure: ${e.message}"
        } catch (e: SocketException) {
            _status.value = "Failure: ${e.message}"
        } catch (e: Exception) {
            _status.value = "Failure: ${e.message}"
        }

    }
}
