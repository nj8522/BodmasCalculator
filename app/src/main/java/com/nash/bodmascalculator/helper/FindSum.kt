package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.main.Calculator
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

open class FindSum {

    //val helperClass = HelperClass()
    val inputProcessor = InputProcessor()
    val calculator = Calculator()

    private var inputOne = 0.0
    private var inputTwo = 0.0


    /**
     * This function is used to calculate the Top two Values from the Values stack with
     * the top Operator from the Operator stack.
     * @author Nash Jacob John
     */
     fun findSum() {


        if (!calculator.valueStack.isEmpty()) {
            if (inputProcessor.getFlag() && calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) > 1 &&
                calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) < 4
            ) {

                try {
                    inputTwo = Double.parseDouble(calculator.valueStack.top())
                    calculator.valueStack.pop()
                    inputOne = Double.parseDouble(calculator.valueStack.top())
                    calculator.valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Please Type in Numbers")
                    calculator.operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    calculator.operatorStack.pop()
                    return
                }
            } else if (inputProcessor.getFlag()) {
                return
            } else {
                try {
                    inputTwo = Double.parseDouble(calculator.valueStack.top())
                    calculator.valueStack.pop()
                    inputOne = Double.parseDouble(calculator.valueStack.top())
                    calculator.valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Enter Number")
                    calculator.operatorStack.pop()
                    return
                } catch (e: Exception) {
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
            when (calculator.operatorStack.top()) {

                "+" -> result = calculator.calculatorHelper.addTwoNumber(inputOne, inputTwo)
                "-" -> result = calculator.calculatorHelper.subTwoNumbers(inputOne, inputTwo)
                "/" -> result = calculator.calculatorHelper.testCaseDiv(inputOne, inputTwo)!!
                "x" -> result = calculator.calculatorHelper.multiplyTwoNumbers(inputOne, inputTwo)

            }
        } catch (e: NullPointerException) {
            println("/ by zero")
            calculator.operatorStack.removeAll()
            calculator.valueStack.removeAll()
            return
        }


        calculator.operatorStack.pop()
        calculator.valueStack.push(result.toString())

    }//fun


}