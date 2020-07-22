package com.nash.bodmascalculator.helper


open class CalculatorTokens {


    private var priorityForSymbol = 0



    /**
     * This function gets operator as an String value and
     * returns the appropriate precedence of the symbol
     * @author Nash Jacob John
     * @param symbol - Gets in an Operator Symbol
     * @return The precedence of the Operator
     */
    fun getPriorityFormExp(symbol: String): Int {
        findPriority(symbol)
        return priorityForSymbol
    }

    /**
     * This function is used to set the precedence of Operator
     * @author Nash Jacob John
     * @param pace - Gets the appropriate value of the Operator
     */
    private fun setPriorityFormExp(pace: Int) {
        priorityForSymbol = pace
    }

    /**
     * This function gets an Operator symbol as String. And uses the When expression
     * to set  the precedence for the operator
     * @author Nash Jacob John
     * @param symbol - Gets the Operator as String
     */
    private fun findPriority(symbol: String) {

        when (symbol) {
            "+" -> setPriorityFormExp(1)
            "-" -> setPriorityFormExp(1)
            "x" -> setPriorityFormExp(2)
            "/" -> setPriorityFormExp(3)
            "(" -> setPriorityFormExp(4)
            ")" -> setPriorityFormExp(5)
        }


    }


}

