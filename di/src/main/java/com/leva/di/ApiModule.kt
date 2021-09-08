package com.leva.di

import com.leva.data.BuildConfig
import com.leva.data.network.RetrofitClient
import com.leva.data.network.api.ProductApi
import org.koin.dsl.module

val apiServiceModule = module {
    single {
        RetrofitClient()
    }
    single<ProductApi> { get<RetrofitClient>().instance().create(ProductApi::class.java) }
}