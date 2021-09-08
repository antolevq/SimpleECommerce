package com.leva.domain

interface BaseMapper<A, B> {
    fun mapFrom(from: A?): B
    fun mapFrom(listA: List<A>): List<B> {
        return listA.map { mapFrom(it) }
    }
}