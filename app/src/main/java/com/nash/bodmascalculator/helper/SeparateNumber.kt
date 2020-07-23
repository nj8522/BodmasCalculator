package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.main.Calculator
import com.nash.bodmascalculator.main.Calculator.Companion.findSumClass
import com.nash.bodmascalculator.main.Calculator.Companion.operatorStack
import com.nash.bodmascalculator.main.Calculator.Companion.valueStack
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException

open class SeparateNumber {

    val inputProcessor = InputProcessor()
    val calculator = Calculator()
    private var separatedNumber = String()


    /**
     * This function is used to separate Values from the expression. After
     *separation the values is stored inside the Value stack
     *@param currentIndex - It Contains the last position of the String
     * @param expression -  It contains the Users expression
     * @author Nash Jacob John
     */
     fun separateNumber(currentIndex: Int, expression: String) {

        separatedNumber = expression.substring(inputProcessor.getFirstIndexOfSeparate(), currentIndex)
        inputProcessor.setFirstIndexOfSeparate(currentIndex + 1)
        try {
            val checkValueIsNumber = Double.parseDouble(separatedNumber)
        } catch (e: IllegalArgumentException) {
            println("Unknown Characters")
            operatorStack.removeAll()
            valueStack.removeAll()
            inputProcessor.setElementOfInputProcessor(expression.length)
            return
        } catch (e: Exception) {
            println(e.message.toString())
        }

        valueStack.push(separatedNumber)


        if (currentIndex == expression.length)
            while (!operatorStack.isEmpty())
                findSumClass.findSum()
    }











}