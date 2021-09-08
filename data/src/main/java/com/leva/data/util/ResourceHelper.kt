package com.leva.data.util


object ResourceHelper {
    fun getJsonString(filename: String): String {
        val stream = this.javaClass.classLoader?.getResourceAsStream(filename)
        val jsonString = stream?.bufferedReader().use { it?.readText() }
        return jsonString ?: ""
    }
}