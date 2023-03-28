package com.example.imagy.network

import com.example.imagy.utils.AccessKey.ACCESS_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.unsplash.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("client_id", ACCESS_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    )
    .build()

interface UnsplashApiService {
    @GET("/photos")
    suspend fun getEditorialFeedPhotos(): Response<List<EditorialFeedPhoto>>

    // Return an instance of UnsplashAPIService as a singleton
    companion object {
        val instance: UnsplashApiService by lazy {
            retrofit.create(UnsplashApiService::class.java)
        }
    }
}
