package com.nash.bodmascalculator.helper



import com.nash.bodmascalculator.InstanceOfClass

open class ExpressionProcessor {


    private val singleton = InstanceOfClass


    private var elementOfInputProcessor = 0
    var  flag : Boolean = false
    var firstIndexOfSeparate = 0


    /**
     * This function get the user expression as an String and calculates the
     * sum and returns as String. This function separates operators into operator stack.
     * @param expression - String expression from the user
     * @return  the sum of the expression
     * @author Nash Jacob John
     */
    fun inputProcessor(expression: String): String {

        elementOfInputProcessor = 0
        firstIndexOfSeparate = 0
        singleton.valueStack.removeAll()
        singleton.operatorStack.removeAll()

        while (elementOfInputProcessor < expression.length) {

            if (expression[elementOfInputProcessor] == '+' ||
                expression[elementOfInputProcessor] == '-' ||
                expression[elementOfInputProcessor] == 'x' ||
                expression[elementOfInputProcessor] == '/' ||
                expression[elementOfInputProcessor] == '(' ||
                expression[elementOfInputProcessor] == ')'

            ) {
                if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4) ||
                    singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)
                ) {

                    if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4)) {


                        flag = true
                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                        firstIndexOfSeparate = elementOfInputProcessor + 1
                        ++elementOfInputProcessor
                        continue
                    }   else if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)) {

                        if (flag) {
                            flag = false
                            if (!singleton.valueStack.isEmpty()) {

                                singleton.separateNumber.separateNumber(elementOfInputProcessor, expression)
                                while (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) != 4) {
                                    singleton.findSumClass.findSum()
                                }
                            }

                            singleton.operatorStack.pop()
                            ++elementOfInputProcessor
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            continue

                        } else if (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()).equals(4)) {

                            singleton.operatorStack.pop()
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            ++elementOfInputProcessor
                            continue
                        } else if (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) < 4) {
                            while (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) != 4) {
                                singleton.findSumClass.findSum()
                            }
                            continue
                        } else {
                            return println("Extra Bracket Can't process the expression").toString()
                        }
                    }

                } else if (singleton.operatorStack.isEmpty()) {

                    if (singleton.valueStack.isEmpty() && elementOfInputProcessor == 0) {

                        if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {
                            //firstIndexOfSeparate = elementOfInputProcessor + 1
                            singleton.operatorStack.removeAll()
                            singleton.valueStack.removeAll()
                            println("Unknown Character At Beginning")
                            elementOfInputProcessor = expression.length
                        }

                        ++elementOfInputProcessor
                        continue
                    } else if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                        ++elementOfInputProcessor
                        continue
                    } else {
                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                    }

                } else if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                           singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top())
                ) {

                    if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {

                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())

                        if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            ++elementOfInputProcessor
                            continue
                        }
                    } else {

                        if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            singleton.findSumClass.findSum()
                            singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                            ++elementOfInputProcessor
                            continue

                        }

                        singleton.separateNumber.separateNumber(elementOfInputProcessor, expression)
                        singleton.findSumClass.findSum()
                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                        ++elementOfInputProcessor
                        continue
                    }


                } else if (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) >
                    singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) || flag
                ) {

                    if (singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {

                        if (singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) in 2..3) {
                            singleton.findSumClass.findSum()
                        }
                        singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }

                    singleton.separateNumber.separateNumber(elementOfInputProcessor, expression)
                    singleton.findSumClass.findSum()
                    while (!singleton.operatorStack.isEmpty() &&
                        singleton.calculatorTokens.getPriorityFormExp(singleton.operatorStack.top()) >
                        singleton.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) && !flag
                    ) {
                        singleton.findSumClass.findSum()
                    }
                    singleton.operatorStack.push(expression[elementOfInputProcessor].toString())
                    ++elementOfInputProcessor
                    continue
                }

                singleton.separateNumber.separateNumber(elementOfInputProcessor, expression)
            }// if
            else if (elementOfInputProcessor == expression.length - 1) {
                singleton.separateNumber.separateNumber(expression.length, expression)
            }


            ++elementOfInputProcessor
        } //while

        if (!singleton.operatorStack.isEmpty()) {
            while (!singleton.operatorStack.isEmpty()) {
                singleton.findSumClass.findSum()
            }
        }

        if (singleton.valueStack.isEmpty()) {
            return "0.0"
        }


        return singleton.valueStack.top()
    }//fun




}