package com.nash.bodmascalculator.main

import com.nash.bodmascalculator.helper.CalculatorHelper
import com.nash.bodmascalculator.helper.CalculatorTokens
import com.nash.bodmascalculator.helper.TokenStack
import java.lang.Double.parseDouble

class Calculator {

    var operatorStack = TokenStack()
    var valueStack = TokenStack()
    var calculatorTokens = CalculatorTokens()
    var calculatorHelper = CalculatorHelper()

    var elementOfInputProcessor = 0


    var separatedNumber = String()
    var firstIndexOfSeparate = 0






    fun inputProcessor(expression : String) : String{

        while (elementOfInputProcessor < expression.length){

              if( expression[elementOfInputProcessor] == '+' ||
                  expression[elementOfInputProcessor] == '-' ||
                  expression[elementOfInputProcessor] == 'x' ||
                  expression[elementOfInputProcessor] == '/' ||
                  expression[elementOfInputProcessor] == '(' ||
                  expression[elementOfInputProcessor] == ')'

              ) {

                  if(operatorStack.isEmpty()){
                      if(valueStack.isEmpty() && elementOfInputProcessor == 0){
                          if(calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1){
                              firstIndexOfSeparate = elementOfInputProcessor + 1
                          }
                          elementOfInputProcessor++
                          continue
                      }else{
                          operatorStack.push(expression[elementOfInputProcessor].toString())
                      }

                  }

                  else if (calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                           calculatorTokens.getPriorityFormExp(operatorStack.top())) {

                      if(calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {
                          operatorStack.push(expression[elementOfInputProcessor].toString())
                      }else{
                          separateNumber(elementOfInputProcessor, expression)
                          findSum()
                          operatorStack.push(expression[elementOfInputProcessor].toString())
                          elementOfInputProcessor++
                          continue
                      }


                  }

                  else if (calculatorTokens.getPriorityFormExp(operatorStack.top()) >
                           calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString())) {

                      separateNumber(elementOfInputProcessor, expression)
                      findSum()
                      if (!operatorStack.isEmpty() &&
                          calculatorTokens.getPriorityFormExp(operatorStack.top()) > calculatorTokens.getPriorityFormExp(
                              expression[elementOfInputProcessor].toString()
                          )
                      ) {
                          findSum()
                      }
                      operatorStack.push(expression[elementOfInputProcessor].toString())
                      elementOfInputProcessor++
                      continue
                  }

                  separateNumber(elementOfInputProcessor,expression)
              }// if
              else if (elementOfInputProcessor == expression.length - 1){
                  separateNumber(expression.length, expression)
              }


            elementOfInputProcessor++
        } //while

        return valueStack.top()
    }//fun

    private  fun separateNumber(currentIndex : Int,expression : String){

        separatedNumber = expression.substring(firstIndexOfSeparate, currentIndex)
        firstIndexOfSeparate = currentIndex + 1
        valueStack.push(separatedNumber)

        if (currentIndex == expression.length)
            while (!operatorStack.isEmpty())
                findSum()
    }



    private fun findSum(){

        var inputOne = 0.0
        var inputTwo = 0.0

        if(!valueStack.isEmpty()){
            inputTwo = parseDouble(valueStack.top())
            valueStack.pop()
            inputOne = parseDouble(valueStack.top())
            valueStack.pop()
        }else{
            println("Empty")
            return
        }

        var result = 0.0

        when(operatorStack.top()){

            "+" -> result = calculatorHelper.addTwoNumber(inputOne,inputTwo)
            "-" -> result = calculatorHelper.subTwoNumbers(inputOne,inputTwo)
            "/" -> result = calculatorHelper.testCaseDiv(inputOne,inputTwo)!!
            "x" -> result = calculatorHelper.multiplyTwoNumbers(inputOne,inputTwo)

        }

        operatorStack.pop()
        valueStack.push(result.toString())
        println(result)
    }

 }


 fun main(){

     val calculator = Calculator()
     val result = calculator.inputProcessor("-42-23+4/32x3")
     println("Result for this expression : $result")



 }
