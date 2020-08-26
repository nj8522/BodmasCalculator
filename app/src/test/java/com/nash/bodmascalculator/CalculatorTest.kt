package com.nash.bodmascalculator

import com.nash.bodmascalculator.helper.ExpressionProcessor
import junit.framework.Assert.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {


    val inputProcessor = ExpressionProcessor()
    val instanceOfClass = InstanceOfClass
    //val calculator = Calculator.calculatorCompanion

    /**
     * The companion Object contains an argument method
     * which is used to set multiple test cases
     * @author Nash
     */
    companion object{
        @JvmStatic
        fun arguments() =
            listOf(
                Arguments.of("24x25/36+42-32","26.666666666666664"),
                Arguments.of("16+33x43-32/5","1428.6"),
                Arguments.of("1/2x12-4+6","8.0"),
                Arguments.of("-42-23+4/32x3","-64.625"),
                Arguments.of("1+2+3x4-4/2x(6-9)","21.0"),
                Arguments.of("(1+2)+(3x4)-(4/2)/(6-9)","15.666666666666666"),
                Arguments.of("32+(64/2+4)/4","41.0"),
                Arguments.of("(1)","0.0"),
                Arguments.of("((3x4)/(4+2)+(123-321))","-196.0"),
                Arguments.of("((3x4)+(4+2)/(123-321)x(456321/23)-(2312+1))","-2902.213438735178"),
                Arguments.of("((3x4))+42/21652342x43","12.000083408991046"),
                Arguments.of("((3x4))/0","0.0"),
                Arguments.of("/56247","0.0"),
                Arguments.of("x53323","0.0"),
                Arguments.of("xsadfasd+sdsd","0.0")
            )
    }

    /**
     * This fun runs multiple tests cases using the assertAll expression
     *@param inputValue - gets the input for the test cases
     *@param expectedValue - It has the  expected output for the test cases
     * @author Nash
     */
    @ParameterizedTest
    @MethodSource("arguments")
    fun expressionTester(inputValue : String, expectedValue : String){
        val actual = instanceOfClass.inputProcessor.inputProcessor(inputValue)
        assertEquals(expectedValue,actual)
    }

}