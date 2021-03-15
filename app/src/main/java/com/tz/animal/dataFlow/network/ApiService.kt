package com.tz.animal.dataFlow.network

import com.google.gson.Gson
import com.tz.animal.BuildConfig
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private val requestInterceptor =
        RequestInterceptor()

    val httpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(createLogger())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(requestInterceptor)
        .followRedirects(true)
        .build()

    private fun createLogger(): Interceptor =
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getApi(): Api =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL.toHttpUrlOrNull()!!)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(httpClient)
            .build()
            .create(Api::class.java)


}