package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.InstanceOfClass
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

open class CalculateExpression {


    val singleton = InstanceOfClass

    private var inputOne = 0.0
    private var inputTwo = 0.0

    var flag : Boolean = false



    /**
     * This function is used to calculate the Top two Values from the Values stack with
     * the top Operator from the Operator stack.
     * @author Nash Jacob John
     */
     fun findSum() {


        if (!singleton.valueStack.isEmpty()) {
            if (singleton.inputProcessor.flag &&
                singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) > 1 &&
                singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) < 4
            ) {

                try {
                    inputTwo = Double.parseDouble(singleton.valueStack.top())
                    singleton.valueStack.pop()
                    inputOne = Double.parseDouble(singleton.valueStack.top())
                    singleton.valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Please Type in Numbers")
                    singleton.operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    singleton.operatorStack.pop()
                    return
                }
            }
            else if (singleton.inputProcessor.flag) {

                return
            }
            else {
                try {
                    inputTwo = Double.parseDouble(singleton.valueStack.top())
                    singleton.valueStack.pop()
                    inputOne = Double.parseDouble(singleton.valueStack.top())
                    singleton.valueStack.pop()
                } catch (e: IllegalArgumentException) {
                    println("Enter Number")
                    singleton.operatorStack.pop()
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
            when (singleton.operatorStack.top()) {

                "+" -> result = singleton.calculatorHelper.addTwoNumber(inputOne, inputTwo)
                "-" -> result = singleton.calculatorHelper.subTwoNumbers(inputOne, inputTwo)
                "/" -> result = singleton.calculatorHelper.testCaseDiv(inputOne, inputTwo)!!
                "x" -> result = singleton.calculatorHelper.multiplyTwoNumbers(inputOne, inputTwo)

            }
        } catch (e: NullPointerException) {
            println("/ by zero")
            singleton.operatorStack.removeAll()
            singleton.valueStack.removeAll()
            return
        }


        singleton.operatorStack.pop()
        singleton.valueStack.push(result.toString())

    }//fun


}