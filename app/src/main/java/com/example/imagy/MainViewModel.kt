package com.example.imagy

import androidx.lifecycle.ViewModel
import com.example.imagy.network.EditorialFeedPhoto
import com.example.imagy.repository.UnsplashApiRepo

class MainViewModel : ViewModel() {
    // this is optional and initialized to null because it is expected that the caller - MainFragment -
    // passes this object in before calling any method to fetch the images.
    var unsplashApiRepo: UnsplashApiRepo? = null

    suspend fun editorialFeedPhotos(): List<EditorialFeedPhoto> {
        val editorialFeedResult = unsplashApiRepo?.getEditorialFeedPhotos()
        if (editorialFeedResult != null && editorialFeedResult.isSuccessful) {
            val editorialFeedPhotoList = editorialFeedResult.body()
            if (!editorialFeedPhotoList.isNullOrEmpty()) {
                return editorialFeedPhotoList
            }
        }
        return emptyList()
    }
}
