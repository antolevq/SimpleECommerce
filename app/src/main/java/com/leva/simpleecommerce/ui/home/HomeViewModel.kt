package com.leva.simpleecommerce.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leva.domain.model.CartResult
import com.leva.domain.model.Product
import com.leva.domain.usecase.ProductUseCase
import com.plexia.domain.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _productList = MutableLiveData<DataState<List<Product>>>()
    val productList: LiveData<DataState<List<Product>>> = _productList


    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productUseCase.getProductList()
                .onStart {
                    emit(DataState.Loading())
                }
                .catch { exception ->
                    emit(DataState.Error(exception.message.orEmpty()))
                }
                .collect {
                    _productList.postValue(it)
                }
        }
    }
}