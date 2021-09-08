package com.leva.data.network

import com.leva.data.BuildConfig
import com.leva.data.network.interceptors.FakeApiInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    fun instance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(FakeApiInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val TIMEOUT_READ = 30L
        private const val TIMEOUT_CONNECT = 30L
    }
}