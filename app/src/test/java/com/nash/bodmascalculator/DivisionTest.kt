package com.nash.bodmascalculator


import com.nash.bodmascalculator.helper.CalculatorHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.ArithmeticException
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DivisionTest {


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
                Arguments.of(7.0f, 0.0f, null),
                Arguments.of(4.0f, 2.0f, 2.0)

            )
    }

    /**
     * This fun runs multiple tests cases using the assertAll expression
     *@param inputOne - gets the first input for the test cases
     * @param inputTwo - gets the second input for the test cases
     *@param expectedValue - It has the  expected output for the test cases
     * @author Nash
     */
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @MethodSource("arguments")
    fun divideTest(inputOne: Double, inputTwo: Double, expectedValue: Double?) {
        val actual = valueFromFun.testCaseDiv(inputOne, inputTwo)

        assertAll("Should return all Assert Cases",
            { assertTrue(expectedValue == actual) },
            { assertEquals(expectedValue, actual) }
        )

    }


}