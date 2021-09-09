package com.leva.data.network.interceptors

import com.leva.data.network.GET_PRODUCTS_ENDPOINT
import com.leva.data.network.POST_CART_ENDPOINT
import com.leva.data.util.ResourceHelper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class FakeApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val calledUri = chain.request().url.toUri().toString()
        val response = when {
            calledUri.endsWith(GET_PRODUCTS_ENDPOINT) ->
                ResourceHelper.getJsonString(
                    GET_PRODUCTS_RESPONSE_FILE
                )
            calledUri.endsWith(POST_CART_ENDPOINT) ->
                ResourceHelper.getJsonString(
                    POST_CART_RESPONSE_FILE
                )
            else -> ""
        }
        return Response.Builder()
            .request(chain.request())
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(response)
            .body(
                response.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()

    }

    companion object {
        const val GET_PRODUCTS_RESPONSE_FILE = "getProductsResponse.json"
        const val POST_CART_RESPONSE_FILE = "postCartResponse.json"
    }
}
