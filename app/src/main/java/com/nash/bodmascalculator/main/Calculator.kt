package com.nash.bodmascalculator.main

import com.nash.bodmascalculator.helper.*

class Calculator {

    val operatorStack = TokenStack()
    val valueStack = TokenStack()
    val calculatorTokens = CalculatorTokens()
    val separateNumber = SeparateNumber()
    val findSumClass = FindSum()
    val calculatorHelper = CalculatorHelper()



//    fun findTheSum(expression : String) : String{
//        return inputProcessor.inputProcessor(expression)
//    }



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val calculator = Calculator()
            calculator.callTheMethod()
            val inputProcessor = InputProcessor()

            println("Enter your Expression: \n")
            val expressionFromUser = readLine().toString()
            val result = inputProcessor.inputProcessor(expressionFromUser)
            println(result)

        }
    }


}






