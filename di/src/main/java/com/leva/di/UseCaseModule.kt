package com.leva.di

import com.leva.domain.usecase.ProductUseCase
import com.leva.domain.usecase.ProductUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase>{
        ProductUseCaseImpl(productRepository = get())
    }
}