package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.main.Calculator
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.calculatorTokens
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.findSumClass
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.operatorStack
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.separateNumber
import com.nash.bodmascalculator.main.Calculator.calculatorCompanion.valueStack

open class InputProcessor {





    private var elementOfInputProcessor = 0
    public var  flag : Boolean = false
        get() = field
        set(value) {field = value}
    var firstIndexOfSeparate = 0

    fun getElementOfInputProcessor() : Int{
        return  elementOfInputProcessor
    }

    fun setElementOfInputProcessor(elementOfInputProcessor : Int){
        this.elementOfInputProcessor = elementOfInputProcessor
    }


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
                if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4) ||
                    calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4)) {


                        flag = true
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        firstIndexOfSeparate = elementOfInputProcessor + 1
                        ++elementOfInputProcessor
                        continue
                    }   else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)) {

                        if (flag) {
                            flag = false
                            if (!valueStack.isEmpty()) {

                                separateNumber.separateNumber(elementOfInputProcessor, expression)
                                while (calculatorTokens.getPriorityFormExp(operatorStack.top()) != 4) {
                                    findSumClass.findSum()
                                }
                            }

                            operatorStack.pop()
                            ++elementOfInputProcessor
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            continue

                        } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()).equals(4)) {

                            operatorStack.pop()
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            ++elementOfInputProcessor
                            continue
                        } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) < 4) {
                            while (calculatorTokens.getPriorityFormExp(operatorStack.top()) != 4) {
                                  findSumClass.findSum()
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

                        ++elementOfInputProcessor
                        continue
                    } else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        ++elementOfInputProcessor
                        continue
                    } else {
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                    }

                } else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                          calculatorTokens.getPriorityFormExp(operatorStack.top())
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {

                        operatorStack.push(expression[elementOfInputProcessor].toString())

                        if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            ++elementOfInputProcessor
                            continue
                        }
                    } else {

                        if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            findSumClass.findSum()
                            operatorStack.push(expression[elementOfInputProcessor].toString())
                            ++elementOfInputProcessor
                            continue

                        }

                        separateNumber.separateNumber(elementOfInputProcessor, expression)
                        findSumClass.findSum()
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        ++elementOfInputProcessor
                        continue
                    }


                } else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) >
                       calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) || flag
                ) {

                    if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {

                        if (calculatorTokens.getPriorityFormExp(operatorStack.top()) in 2..3) {
                            findSumClass.findSum()
                        }
                        operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }

                    separateNumber.separateNumber(elementOfInputProcessor, expression)
                    findSumClass.findSum()
                    while (!operatorStack.isEmpty() &&
                        calculatorTokens.getPriorityFormExp(operatorStack.top()) >
                        calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) && !flag
                    ) {
                        findSumClass.findSum()
                    }
                    operatorStack.push(expression[elementOfInputProcessor].toString())
                    ++elementOfInputProcessor
                    continue
                }

                separateNumber.separateNumber(elementOfInputProcessor, expression)
            }// if
            else if (elementOfInputProcessor == expression.length - 1) {
                separateNumber.separateNumber(expression.length, expression)
            }


            ++elementOfInputProcessor
        } //while

        if (!operatorStack.isEmpty()) {
            while (!operatorStack.isEmpty()) {
                findSumClass.findSum()
            }
        }

        if (valueStack.isEmpty()) {
            return "0.0"
        }
        return valueStack.top()
    }//fun




}