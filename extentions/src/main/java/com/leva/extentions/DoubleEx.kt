package com.leva.extentions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.math.round

/**
 * Extention function that convert a double into a Euro currency
 *
 * @return  Euro currency value
 */
fun Double.convertToPresentation(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.ITALY)
    return format.format(this.round())
}

/**
 * Extention function that round a double
 *
 * @return  Rounded double
 */
fun Double.round(): Double {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}