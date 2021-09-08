package com.leva.di

import com.leva.data.repository.ProductRepositoryImpl
import com.leva.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ProductRepository>{
        ProductRepositoryImpl(productApi = get(), productMapper = get(), cartResultMapper = get())
    }
}