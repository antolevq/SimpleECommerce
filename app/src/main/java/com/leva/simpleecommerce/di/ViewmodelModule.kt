package com.leva.simpleecommerce.di

import com.leva.simpleecommerce.ui.home.HomeViewModel
import com.leva.simpleecommerce.viewmodels.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModuleModule = module {
    viewModel { HomeViewModel(productUseCase = get()) }
    viewModel { CartViewModel(productUseCase = get()) }
}