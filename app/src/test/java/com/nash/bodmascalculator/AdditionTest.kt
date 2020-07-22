package com.nash.bodmascalculator

import com.nash.bodmascalculator.helper.CalculatorHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class AdditionTest {

    private var valueFromFun = CalculatorHelper()

    /**
     * The companion Object contains an argument method
     * which is used to set multiple test cases
     * @author Nash
     */
    private companion object {
        @JvmStatic
        fun arguments() =
            listOf(
                Arguments.of(42.0f, 85.0f, 127.0),
                Arguments.of(0.0f, 63.0f, 63.0)
            )
    }


    /**
     * This fun runs multiple tests cases using the assertAll expression
     *@param inputOne - gets the first input for the test cases
     * @param inputTwo - gets the second input for the test cases
     *@param expectedValue - It has the  expected output for the test cases
     * @author Nash
     */
    @ParameterizedTest
    @MethodSource("arguments")
    fun additionTest(inputOne: Double, inputTwo: Double, expectedValue: Double?) {
        val actual = valueFromFun.addTwoNumber(inputOne, inputTwo)

        assertAll("AseertEquels Addition",
            { assertEquals(expectedValue, actual) },
            { assertEquals(expectedValue, actual) }
        )
    }


}