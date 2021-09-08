package com.leva.simpleecommerce

import android.app.Application
import com.leva.di.apiServiceModule
import com.leva.di.mapperModule
import com.leva.di.repositoryModule
import com.leva.di.useCaseModule
import com.leva.simpleecommerce.di.viewModuleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ECommerceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ECommerceApplication)
            modules(listOf(
                apiServiceModule,
                mapperModule,
                repositoryModule,
                useCaseModule,
                viewModuleModule
            ))
        }
    }
}