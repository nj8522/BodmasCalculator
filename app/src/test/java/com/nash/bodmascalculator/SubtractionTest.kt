package com.nash.bodmascalculator

import com.nash.bodmascalculator.helper.CalculatorHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

class SubtractionTest {

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
                Arguments.of(-250.0f, 270.0f, -520.0),
                Arguments.of(43.0f, 0.0f, 43.0)

            )
    }


    /**
     * This fun runs multiple tests cases using the assertAll expression
     *@param inputOne - gets the first input for the test cases
     * @param inputTwo - gets the second input for the test cases
     *@param expectedValues - It has the  expected output for the test cases
     * @author Nash
     */
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @MethodSource("arguments")
    fun subtractionTest(inputOne: Double, inputTwo: Double, expectedValues: Double?) {
        val actual = valueFromFun.subTwoNumbers(inputOne, inputTwo)

        assertAll("Assert Equals",
            { assertEquals(expectedValues, actual) },
            { assertEquals(expectedValues, actual) }
        )
    }


}