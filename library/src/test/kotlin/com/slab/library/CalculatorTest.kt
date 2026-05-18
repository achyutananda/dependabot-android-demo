package com.slab.library

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CalculatorTest {

    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun `add returns correct sum`() {
        assertEquals(5, calculator.add(2, 3))
    }

    @Test
    fun `subtract returns correct difference`() {
        assertEquals(1, calculator.subtract(3, 2))
    }

    @Test
    fun `multiply returns correct product`() {
        assertEquals(6, calculator.multiply(2, 3))
    }

    @Test
    fun `divide returns correct quotient`() {
        assertEquals(3, calculator.divide(9, 3))
    }

    @Test
    fun `divide by zero throws IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.divide(1, 0)
        }
    }
}
