package com.nash.bodmascalculator.helper

import com.nash.bodmascalculator.main.Calculator

open class InputProcessor {

    //val helperClass = HelperClass()

//    val operatorStack = TokenStack()
//    val valueStack = TokenStack()
//    val calculatorTokens = CalculatorTokens()
//    val separateNumber = SeparateNumber()
//    val findSumClass = FindSum()
//    val calculatorHelper = CalculatorHelper()
      val calculator = Calculator()

    private var elementOfInputProcessor = 0
    private var  flag : Boolean = false
    private var firstIndexOfSeparate = 0

    fun getElementOfInputProcessor() : Int{
        return  elementOfInputProcessor
    }

    fun setElementOfInputProcessor(elementOfInputProcessor : Int){
        this.elementOfInputProcessor = elementOfInputProcessor
    }

    fun getFlag() : Boolean{
        return  flag
    }

    fun setFlag(flag : Boolean){
        this.flag = flag
    }

    fun getFirstIndexOfSeparate(): Int{
        return  firstIndexOfSeparate
    }

    fun setFirstIndexOfSeparate(firstIndexOfSeparator : Int){
        this.firstIndexOfSeparate = firstIndexOfSeparator
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
                if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 4 ||
                    calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 5
                ) {

                    if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 4) {


                        setFlag(true)
                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                        setFirstIndexOfSeparate(elementOfInputProcessor + 1)
                        elementOfInputProcessor++
                        continue
                    }   else if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) == 5) {

                        if (getFlag()) {
                            flag = false
                            if (!calculator.valueStack.isEmpty()) {

                                calculator.separateNumber.separateNumber(elementOfInputProcessor, expression)
                                while (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) != 4) {
                                    calculator.findSumClass.findSum()
                                }
                            }

                            calculator.operatorStack.pop()
                            elementOfInputProcessor++
                            setFirstIndexOfSeparate(elementOfInputProcessor + 1)
                            continue

                        } else if (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) == 4) {

                            calculator.operatorStack.pop()
                            elementOfInputProcessor++
                            setFirstIndexOfSeparate(elementOfInputProcessor + 1)
                            continue
                        } else if (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) < 4) {
                            while (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) != 4) {
                                calculator.findSumClass.findSum()
                            }
                            continue
                        } else {
                            return println("Extra Bracket Can't process the expression").toString()
                        }
                    }

                } else if (calculator.operatorStack.isEmpty()) {

                    if (calculator.valueStack.isEmpty() && elementOfInputProcessor == 0) {

                        if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {
                            //firstIndexOfSeparate = elementOfInputProcessor + 1
                            calculator.operatorStack.removeAll()
                            calculator.valueStack.removeAll()
                            println("Unknown Character At Beginning")
                            elementOfInputProcessor = expression.length
                        }

                        elementOfInputProcessor++
                        continue
                    } else if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    } else {
                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                    }

                } else if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                    calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top())
                ) {

                    if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {

                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())

                        if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                            elementOfInputProcessor++
                            continue
                        }
                    } else {

                        if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {
                            calculator.findSumClass.findSum()
                            calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                            elementOfInputProcessor++
                            continue

                        }

                        calculator.separateNumber.separateNumber(elementOfInputProcessor, expression)
                        calculator.findSumClass.findSum()
                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }


                } else if (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) >
                    calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) || flag
                ) {

                    if (calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()) == 5) {

                        if (calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) in 2..3) {
                            calculator.findSumClass.findSum()
                        }
                        calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }

                    calculator.separateNumber.separateNumber(elementOfInputProcessor, expression)
                    calculator.findSumClass.findSum()
                    while (!calculator.operatorStack.isEmpty() &&
                        calculator.calculatorTokens.getPriorityFormExp(calculator.operatorStack.top()) >
                        calculator.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) && !flag
                    ) {
                        calculator.findSumClass.findSum()
                    }
                    calculator.operatorStack.push(expression[elementOfInputProcessor].toString())
                    elementOfInputProcessor++
                    continue
                }

                calculator.separateNumber.separateNumber(elementOfInputProcessor, expression)
            }// if
            else if (elementOfInputProcessor == expression.length - 1) {
                calculator.separateNumber.separateNumber(expression.length, expression)
            }


            elementOfInputProcessor++
        } //while

        if (!calculator.operatorStack.isEmpty()) {
            while (!calculator.operatorStack.isEmpty()) {
                calculator.findSumClass.findSum()
            }
        }

        if (calculator.valueStack.isEmpty()) {
            return "0.0"
        }
        return calculator.valueStack.top()
    }//fun




}