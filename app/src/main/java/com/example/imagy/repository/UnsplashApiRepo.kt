package com.example.imagy.repository

import com.example.imagy.network.UnsplashApiService

class UnsplashApiRepo(private val unsplashApiService: UnsplashApiService) {
    suspend fun editorialFeedPhotos() = unsplashApiService.getEditorialFeedPhotos()
}
