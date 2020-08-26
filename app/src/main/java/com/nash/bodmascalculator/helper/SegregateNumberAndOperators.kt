package com.nash.bodmascalculator.helper


import com.nash.bodmascalculator.InstanceOfClass
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.reflect.InvocationTargetException

open class SegregateNumberAndOperators {

    private var seperatedValue = String()

    val singleton = InstanceOfClass

    /**
     * This function is used to separate Values from the expression. After
     *separation the values is stored inside the Value stack
     *@param currentIndex - It Contains the last position of the String
     * @param expression -  It contains the Users expression
     * @author Nash Jacob John
     */
     fun separateNumber(currentIndex: Int, expression: String) {

        try {
            seperatedValue = expression.substring(singleton.inputProcessor.firstIndexOfSeparate, currentIndex)
        } catch (e : InvocationTargetException){
            println(e.message.toString())
        }

        singleton.inputProcessor.firstIndexOfSeparate = currentIndex + 1
        try {
            val checkValueIsNumber = Double.parseDouble(seperatedValue)
        } catch (e: IllegalArgumentException) {
            println("Unknown Characters")
            singleton.operatorStack.removeAll()
            singleton.valueStack.removeAll()
            singleton.inputProcessor.firstIndexOfSeparate = expression.length
            return
        } catch (e: Exception) {
            println(e.message.toString())
        }

        singleton.valueStack.push(seperatedValue)


        if (currentIndex == expression.length)
            while (!singleton.operatorStack.isEmpty())
                singleton.findSumClass.findSum()
    }











}