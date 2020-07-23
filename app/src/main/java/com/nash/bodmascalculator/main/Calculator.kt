package com.nash.bodmascalculator.main

import com.nash.bodmascalculator.helper.*

class Calculator {





//    fun findTheSum(expression : String) : String{
//        return inputProcessor.inputProcessor(expression)
//    }



    companion object {
        public lateinit var operatorStack : TokenStack
        public lateinit var valueStack : TokenStack
        public lateinit var  calculatorTokens : CalculatorTokens
        public lateinit var  separateNumber : SeparateNumber
        public lateinit var  findSumClass : FindSum
        public lateinit var  calculatorHelper : CalculatorHelper
        public lateinit var  inputProcessor : InputProcessor

        @JvmStatic
        fun main(args: Array<String>) {

            operatorStack = TokenStack()
            valueStack = TokenStack()
            calculatorTokens = CalculatorTokens()
            separateNumber = SeparateNumber()
            findSumClass = FindSum()
            calculatorHelper = CalculatorHelper()
            inputProcessor = InputProcessor()

            val cal = Calculator()
            println("Enter your Expression: \n")
            val expressionFromUser = readLine().toString()
            val result = inputProcessor.inputProcessor(expressionFromUser)
            println(result)

        }
    }


}






