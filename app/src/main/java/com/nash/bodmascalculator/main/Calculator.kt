package com.nash.bodmascalculator.main

import com.nash.bodmascalculator.helper.CalculatorHelper
import com.nash.bodmascalculator.helper.CalculatorTokens
import com.nash.bodmascalculator.helper.TokenStack
import java.lang.Double.parseDouble
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class Calculator {

    private  var operatorStack = TokenStack()
    private var valueStack = TokenStack()
    private var calculatorTokens = CalculatorTokens()
    private var calculatorHelper = CalculatorHelper()

    private var elementOfInputProcessor = 0


    private var separatedNumber = String()
    private var firstIndexOfSeparate = 0

    private var indexFromFun = 0

    private var flag: Boolean = false

    private var inputOne = 0.0
    private var inputTwo = 0.0

    /**
     * This function get the user expression as an String and calculates the
     * sum and returns as String. This function separates operators into operator stack.
     * @param expression - String expression from the user
     * @return  the sum of the expression
     * @author Nash Jacob John
     */
    fun inputProcessor(expression: String): String {

        while (elementOfInputProcessor < expression.length) {

            if (expression[elementOfInputProcessor] == '+' ||
                expression[elementOfInputProcessor] == '-' ||
                expression[elementOfInputProcessor] == 'x' ||
                expression[elementOfInputProcessor] == '/' ||
                expression[elementOfInputProcessor] == '(' ||
                expression[elementOfInputProcessor] == ')'

            ) {
                if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 4 ||
                    calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 5
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 4) {

                        flag = true
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        firstIndexOfSeparate = elementOfInputProcessor + 1
                        elementOfInputProcessor++
                        continue
                    } else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 5) {

                        if (flag) {
                            flag = false
                            if (!valueStack.isEmpty()) {

                                separateNumber(elementOfInputProcessor, expression)
                                while (calculatorTokens.getPriorityFormExp(operatorStack.top()) != 4) {
                                    findSum()
                                }
                            }

                            operatorStack.pop()
                            elementOfInputProcessor++
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            continue

                        } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) == 4) {

                            operatorStack.pop()
                            elementOfInputProcessor++
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            continue
                        } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) < 4) {
                            while (calculatorTokens.getPriorityFormExp(operatorStack.top()) != 4) {
                                findSum()
                            }
                            continue
                        } else {
                            return println("Extra Bracket Can't process the expression").toString()
                        }
                    }

                } else if (operatorStack.isEmpty()) {


                    if (valueStack.isEmpty() && elementOfInputProcessor == 0) {

                            if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {
                                //firstIndexOfSeparate = elementOfInputProcessor + 1
                                operatorStack.removeAll()
                                valueStack.removeAll()
                                println("Unknown Character At Beginning")
                                elementOfInputProcessor = expression.length
                            }

                        elementOfInputProcessor++
                        continue
                    } else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    } else {
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                    }

                } else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                    calculatorTokens.getPriorityFormExp(operatorStack.top())
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {

                        operatorStack.push(expression[elementOfInputProcessor].toString())

                        if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                            elementOfInputProcessor++
                            continue
                        }
                    } else {

                        if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                            findSum()
                            operatorStack.push(expression[elementOfInputProcessor].toString())
                            elementOfInputProcessor++
                            continue

                        }

                        separateNumber(elementOfInputProcessor, expression)
                        findSum()
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }


                } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) >
                    calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) || flag
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {

                        if (calculatorTokens.getPriorityFormExp(operatorStack.top()) in 2..3) {
                            findSum()
                        }
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }

                    separateNumber(elementOfInputProcessor, expression)
                    findSum()
                    while (!operatorStack.isEmpty() &&
                        calculatorTokens.getPriorityFormExp(operatorStack.top()) >
                        calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) && !flag
                    ) {
                        findSum()
                    }
                    operatorStack.push(expression[elementOfInputProcessor].toString())
                    elementOfInputProcessor++
                    continue
                }

                separateNumber(elementOfInputProcessor, expression)
            }// if
            else if (elementOfInputProcessor == expression.length - 1) {
                separateNumber(expression.length, expression)
            }


            elementOfInputProcessor++
        } //while

        if(!operatorStack.isEmpty()){
            while (!operatorStack.isEmpty()) {
                findSum()
            }
        }

        if (valueStack.isEmpty()) {
            return "0.0"
        }
        return valueStack.top()
    }//fun

    /**
    * This function is used to separate Values from the expression. After
     *separation the values is stored inside the Value stack
     *@param currentIndex - It Contains the last position of the String
     * @param expression -  It contains the Users expression
     * @author Nash Jacob John
    */
    private fun separateNumber(currentIndex: Int, expression: String)  {

        separatedNumber = expression.substring(firstIndexOfSeparate, currentIndex)
        firstIndexOfSeparate = currentIndex + 1
        try {
            val checkValueIsNumber = parseDouble(separatedNumber)
        }
        catch (e : IllegalArgumentException){
            println("Unknown Characters")
            operatorStack.removeAll()
            valueStack.removeAll()
            elementOfInputProcessor = expression.length
            return
        }
        catch (e : Exception){
            println(e.message.toString())
        }

        valueStack.push(separatedNumber)


        if (currentIndex == expression.length)
            while (!operatorStack.isEmpty())
                findSum()
    }

    /**
    * This function is used to calculate the Top two Values from the Values stack with
     * the top Operator from the Operator stack.
    * @author Nash Jacob John
    */
    private fun findSum() {


        if (!valueStack.isEmpty()) {
            if (flag && calculatorTokens.getPriorityFormExp(operatorStack.top()) > 1 && calculatorTokens.getPriorityFormExp(
                    operatorStack.top()
                ) < 4
            ) {

                try {
                    inputTwo = parseDouble(valueStack.top())
                    valueStack.pop()
                    inputOne = parseDouble(valueStack.top())
                    valueStack.pop()
                }
                catch (e: IllegalArgumentException) {
                    println("Please Type in Numbers")
                    operatorStack.pop()
                    return
                }
                catch (e: Exception) {
                    println(e.message.toString())
                    operatorStack.pop()
                    return
                }
            } else if (flag) {
                return
            }

            else {
                try {
                    inputTwo = parseDouble(valueStack.top())
                    valueStack.pop()
                    inputOne = parseDouble(valueStack.top())
                    valueStack.pop()
                }
                catch (e: IllegalArgumentException) {
                    println("Enter Number")
                    operatorStack.pop()
                    return
                }
                catch (e: Exception) {
                    println(e.message.toString())
                    return
                }
            }

        } else {
            println("Empty")
            return
        }


        var result = 0.0

        try {
            when (operatorStack.top()) {

                "+" -> result = calculatorHelper.addTwoNumber(inputOne, inputTwo)
                "-" -> result = calculatorHelper.subTwoNumbers(inputOne, inputTwo)
                "/" -> result = calculatorHelper.testCaseDiv(inputOne, inputTwo)!!
                "x" -> result = calculatorHelper.multiplyTwoNumbers(inputOne, inputTwo)

            }
        } catch (e: NullPointerException) {
            println("/ by zero")
            operatorStack.removeAll()
            return
        }


        operatorStack.pop()
        valueStack.push(result.toString())


    }//fun


}


fun main() {

    val calculator = Calculator()

    var result = String()
    println("Enter the Expression or Press X to quit")
    val expressionFromUser = readLine().toString()

    while (true){
        if(expressionFromUser == "x") {
            break
        }

        result = calculator.inputProcessor(expressionFromUser)
        println("Results for this Expression is : \n" +
                result +
                "\n"
        )
        return main()
    }

}




