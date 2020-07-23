package com.nash.bodmascalculator.main

import com.nash.bodmascalculator.helper.*

class Calculator {



    companion object calculatorCompanion{
        public lateinit var operatorStack : TokenStack
        public lateinit var valueStack : TokenStack
        public lateinit var  calculatorTokens : CalculatorTokens
        public lateinit var  separateNumber : SeparateNumber
        public lateinit var  findSumClass : FindSum
        public lateinit var  calculatorHelper : CalculatorHelper
        public lateinit var  inputProcessor : InputProcessor



        @JvmStatic
        fun main(args: Array<String>) {

            Calculator.operatorStack = TokenStack()
            Calculator.valueStack = TokenStack()
            Calculator.calculatorTokens = CalculatorTokens()
            Calculator.separateNumber = SeparateNumber()
            Calculator.findSumClass = FindSum()
            Calculator.calculatorHelper = CalculatorHelper()
            Calculator.inputProcessor = InputProcessor()

            var expressionFromUser = String()
            var  result = String()

            while (true){
                println("Enter Your Expression or press X to exit")
                expressionFromUser = readLine().toString()
                if (expressionFromUser.equals("x")) {
                    break
                }
                result = inputProcessor.inputProcessor(expressionFromUser)
                println("Result : $result")

            }

          }

        fun findTheSum(expression : String) : String{
            return inputProcessor.inputProcessor(expression)

        }
    }





}










