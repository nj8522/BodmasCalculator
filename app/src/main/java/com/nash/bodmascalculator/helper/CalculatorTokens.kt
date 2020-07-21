package com.nash.bodmascalculator.helper


open class CalculatorTokens {


        var priorityForSymbol = 0

        fun getPriorityFormExp(symbol : String) : Int{
           findPriority(symbol)
           return priorityForSymbol
    }

     fun setPriorityFormExp(pace : Int){
         priorityForSymbol = pace
     }


    fun findPriority(symbol : String){

        when(symbol){
            "+" -> setPriorityFormExp(1)
            "-" -> setPriorityFormExp(1)
            "x" -> setPriorityFormExp(2)
            "/" -> setPriorityFormExp(3)
            "(" -> setPriorityFormExp(4)
            ")" -> setPriorityFormExp(5)
        }


    }





}

