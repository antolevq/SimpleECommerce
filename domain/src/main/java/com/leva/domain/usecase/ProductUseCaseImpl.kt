package com.leva.domain.usecase

import com.leva.domain.model.CartResult
import com.leva.domain.model.Product
import com.leva.domain.repository.ProductRepository
import com.plexia.domain.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext

class ProductUseCaseImpl(
    private val productRepository: ProductRepository
) : ProductUseCase {
    override fun getProductList(): Flow<DataState<List<Product>>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                async {
                    productRepository.getProductList()
                }
            }.await()
            emit(
                DataState.Success(response)
            )
        }
            .retry(RETRY_COUNT)
            .flowOn(Dispatchers.Default)


    }

    override fun postCart(totalAmount: Double, totalItems: Int): Flow<DataState<CartResult>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                async {
                    productRepository.postCart(totalAmount = totalAmount, totalItems = totalItems)
                }
            }.await()
            emit(
                DataState.Success(response)
            )
        }
            .retry(RETRY_COUNT)
            .flowOn(Dispatchers.Default)
    }

    companion object {
        private const val RETRY_COUNT: Long = 2
    }
}