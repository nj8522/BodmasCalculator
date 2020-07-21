package com.nash.bodmascalculator

import com.nash.bodmascalculator.main.Calculator
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {

    val calculator = Calculator()



    companion object{
        @JvmStatic
        fun arguments() =
            listOf(
                Arguments.of("24x25/36+42-32","26.666666666666664"),
                Arguments.of("16+33x43-32/5","1428.6"),
                Arguments.of("1/2x12-4+6","8.0"),
                Arguments.of("-42-23+4/32x3","-64.625")
            )
    }



    @ParameterizedTest
    @MethodSource("arguments")
    fun expressionTester(inputValue : String, expectedValue : String){
        val actual = calculator.inputProcessor(inputValue)

        assertEquals(expectedValue,actual)
    }





}