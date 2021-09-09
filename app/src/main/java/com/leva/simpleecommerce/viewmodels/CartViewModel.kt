package com.leva.simpleecommerce.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leva.domain.model.CartItem
import com.leva.domain.model.CartResult
import com.leva.domain.model.Product
import com.leva.domain.usecase.ProductUseCase
import com.leva.extentions.convertToPresentation
import com.leva.extentions.round
import com.plexia.domain.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CartViewModel(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _cartItemList = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItemList: LiveData<MutableList<CartItem>> = _cartItemList

    private val _totalAmount = MutableLiveData<String>()
    val totalAmount: LiveData<String> = _totalAmount

    private val _totalCartItems = MutableLiveData<Int>(0)
    val totalCartItems: LiveData<Int> = _totalCartItems

    val cartResponse = SingleLiveEvent<DataState<CartResult>>()


    /**
     * Send the cart to the server via the usecase.
     * To simulate the server call, a delay has been set after the emit of loading
     */
    fun postCart() {
        viewModelScope.launch {
            val totalAmount =
                _cartItemList.value?.sumOf { (it.quantity * it.product.price) } ?: 0.toDouble()
                    .round()
            val totalItems = _cartItemList.value?.sumOf { it.quantity } ?: 0

            productUseCase.postCart(totalAmount = totalAmount, totalItems = totalItems)
                .onStart {
                    emit(DataState.Loading())
                    delay(2000) //Simulate server delay
                }
                .catch { exception ->
                    emit(DataState.Error(exception.message.orEmpty()))
                }
                .collect {
                    cartResponse.postValue(it)
                }
        }
    }

    /**
     * Update CartItem list
     * This function can add an element if not present, or modify one if present.
     * Quantity set to 0 delete item from list
     * @param quantity  an int that represents how many products of that type the cart is made up of
     * @param product   an Product to manipulate
     */
    fun updateItemsQuantity(quantity: Int, product: Product) {
        when (quantity) {
            0 -> {
                _cartItemList.value?.removeAll { it.product.id == product.id }
                _cartItemList.postValue(_cartItemList.value)

            }
            else -> {
                _cartItemList.value?.let { safeCartItems ->
                    safeCartItems.firstOrNull { it.product.id == product.id }?.let {
                        it.quantity = quantity
                    } ?: _cartItemList.value?.add(CartItem(quantity, product))
                    _cartItemList.postValue(_cartItemList.value)
                } ?: _cartItemList.postValue(listOf(CartItem(quantity, product)).toMutableList())
            }
        }


        updateTotalAmount()
        updateTotalCartItems()
    }


    /**
     * This function update the total Amount to show in view. It returns in euro currency format
     */
    private fun updateTotalAmount() {
        _totalAmount.postValue(_cartItemList.value?.sumOf { (it.quantity * it.product.price) }
            ?.convertToPresentation())
    }

    /**
     * This function update a livedata with the sum of all products presents in the cart
     */
    private fun updateTotalCartItems() {
        _totalCartItems.postValue(_cartItemList.value?.sumOf { it.quantity } ?: 0)
    }

    /**
     * This function empty cartItemList
     */
    fun emptyCart() {
        _cartItemList.value = mutableListOf()
        _cartItemList.postValue(_cartItemList.value)
        updateTotalAmount()
        updateTotalCartItems()
    }

}