package com.leva.extentions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.math.round


fun Double.convertToPresentation(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.ITALY)
    return format.format(this.round())
}

fun Double.round(): Double {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}