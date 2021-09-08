package com.plexia.domain

import android.os.Parcelable

sealed class DataState<T> {
    class Loading<T> : DataState<T>()

    class Error<T>(val errorMessage: String) : DataState<T>()

    class Success<T>(val data: T) : DataState<T>()
}