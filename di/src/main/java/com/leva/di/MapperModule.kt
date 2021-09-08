package com.leva.di

import com.leva.data.mappers.CartResultMapper
import com.leva.data.mappers.ProductMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { ProductMapper() }
    factory { CartResultMapper() }
}