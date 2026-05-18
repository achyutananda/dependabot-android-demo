package com.slab.library

/**
 * Simple calculator demonstrating library documentation via DokkaHtml.
 */
class Calculator {

    /** Returns the sum of [a] and [b]. */
    fun add(a: Int, b: Int): Int = a + b

    /** Returns the difference of [a] and [b]. */
    fun subtract(a: Int, b: Int): Int = a - b

    /** Returns the product of [a] and [b]. */
    fun multiply(a: Int, b: Int): Int = a * b

    /**
     * Returns the quotient of [a] divided by [b].
     * @throws ArithmeticException if [b] is zero.
     */
    fun divide(a: Int, b: Int): Int {
        require(b != 0) { "Divisor must not be zero" }
        return a / b
    }
}
