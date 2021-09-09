package com.leva.data

import com.leva.data.mappers.CartResultMapper
import com.leva.data.mappers.ProductMapper
import com.leva.data.network.RetrofitClient
import com.leva.data.network.api.ProductApi
import com.leva.data.network.interceptors.FakeApiInterceptor.Companion.GET_PRODUCTS_RESPONSE_FILE
import com.leva.data.network.interceptors.FakeApiInterceptor.Companion.POST_CART_RESPONSE_FILE
import com.leva.data.repository.ProductRepositoryImpl
import com.leva.data.util.ResourceHelper
import com.leva.domain.repository.ProductRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.inject
import kotlin.random.Random

class ResponseUnitTest : KoinTest {

    private val productRepository: ProductRepository by inject(named("product_repository"))

    @get:Rule
    val kointTestRule = KoinTestRule.create {
        loadKoinModules(listOf(module {
            single<MockWebServer> { MockWebServer() }
            single { RetrofitClient(get<MockWebServer>().url("").toString()) }
            single<ProductApi> { get<RetrofitClient>().instance().create(ProductApi::class.java) }
            factory { ProductMapper() }
            factory { CartResultMapper() }
            factory<ProductRepository>(named("product_repository")) {
                ProductRepositoryImpl(
                    productApi = get(),
                    productMapper = get(),
                    cartResultMapper = get()
                )
            }
        }))
    }


    @Test
    fun `get all product assert success`() {
        get<MockWebServer>().apply {
            enqueue(MockResponse().setBody(ResourceHelper.getJsonString(GET_PRODUCTS_RESPONSE_FILE)))
        }

        val productList = runBlocking { productRepository.getProductList() }
        assert(productList.size == 3)
    }

    @Test
    fun `post shopping cart assert success`() {
        get<MockWebServer>().apply {
            enqueue(MockResponse().setBody(ResourceHelper.getJsonString(POST_CART_RESPONSE_FILE)))
        }

        val shoppingCartResponse =
            runBlocking { productRepository.postCart(Random.nextDouble(), Random.nextInt()) }
        assert(shoppingCartResponse.description == "Successful")
    }


    companion object {

    }

}