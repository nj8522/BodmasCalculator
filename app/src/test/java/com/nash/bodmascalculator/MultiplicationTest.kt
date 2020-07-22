package com.nash.bodmascalculator


import com.nash.bodmascalculator.helper.CalculatorHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

class MultiplicationTest {

    private var valueFromFun = CalculatorHelper()

    /**
     * The companion Object contains an argument method
     * which is used to set multiple test cases
     * @author Nash
     */
    companion object {
        @JvmStatic
        fun arguments() =
            listOf(
                Arguments.of(24.0f, 2.0f, 48.0),
                Arguments.of(32.0f, 5.0f, 160.0)

            )
    }

    /**
     * This fun runs multiple tests cases using the assertAll expression
     *@param inputOne - gets the first input for the test cases
     * @param inputTwo - gets the second input for the test cases
     *@param expectedValue - It has the  expected output for the test cases
     * @author Nash
     */
    @ParameterizedTest(name = "{0} x {1} = {2}")
    @MethodSource("arguments")
    fun multiplyTest(inputOne: Double, inputTwo: Double, expectedValue: Double?) {
        val actual = valueFromFun.multiplyTwoNumbers(inputOne, inputTwo)

        assertAll("Multiplying all the Assertions",
            { assertEquals(expectedValue, actual) },
            { assertEquals(expectedValue, actual) }
        )

    }


}