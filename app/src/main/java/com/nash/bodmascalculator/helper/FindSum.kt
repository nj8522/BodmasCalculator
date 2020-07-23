package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.main.Calculator
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.calculatorHelper
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.calculatorTokens
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.inputProcessor
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.operatorStack
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.valueStack
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

open class FindSum {




    private var inputOne = 0.0
    private var inputTwo = 0.0

    var flag : Boolean = false



    /**
     * This function is used to calculate the Top two Values from the Values stack with
     * the top Operator from the Operator stack.
     * @author Nash Jacob John
     */
     fun findSum() {


        if (!valueStack.isEmpty()) {
            if (inputProcessor.flag &&
                calculatorTokens.getPriorityFormExp(operatorStack.top()) > 1 &&
                calculatorTokens.getPriorityFormExp(operatorStack.top()) < 4
            ) {

                try {
                    inputTwo = Double.parseDouble(valueStack.top())
                    valueStack.pop()
                    inputOne = Double.parseDouble(valueStack.top())
                    valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Please Type in Numbers")
                    operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    operatorStack.pop()
                    return
                }
            }
            else if (inputProcessor.flag) {

                return
            }
            else {
                try {
                    inputTwo = Double.parseDouble(valueStack.top())
                    valueStack.pop()
                    inputOne = Double.parseDouble(valueStack.top())
                    valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Enter Number")
                    operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    return
                }
            }

        }
        else {
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
            valueStack.removeAll()
            return
        }


        operatorStack.pop()
        valueStack.push(result.toString())

    }//fun


}