package com.nash.bodmascalculator.helper


open class CalculatorHelper {

    /**
     * This method return sums of two numbers
     * @author Nash
     * @param numberOne - this is number One
     * @param numberTwo - this is number  Two
     * @return sum of numberOne and numberTwo
     */
    fun addTwoNumber(numberOne: Double, numberTwo: Double): Double {

        return numberOne + numberTwo

    }

    /**
     * This method return subtraction of two numbers
     * @author Nash
     * @param numberOne - this is number One
     * @param numberTwo - this is number  Two
     * @return subtraction of numberOne and numberTwo
     */
    fun subTwoNumbers(numberOne: Double, numberTwo: Double): Double {
        return numberOne - numberTwo
    }

    /**
     * This method return Multiplied value of two numbers
     * @author Nash
     * @param numberOne - this is number One
     * @param numberTwo - this is number  Two
     * @return numberOne times numberTwo
     */
    fun multiplyTwoNumbers(numberOne: Double, numberTwo: Double): Double {
        return numberOne * numberTwo
    }


//    /**
//     * This method return quotient of two numbers
//     * @author Nash
//     * @param numberOne - this is number One
//     * @param numberTwo - this is number  Two
//     * @return dividend of numberOne and numberTwo
//     */
//    fun divTwoNumbers(numberOne: Float, numberTwo: Float): Float {
//        if (numberTwo == 0.0f) {
//            throw ArithmeticException()
//        }
//        return numberOne / numberTwo
//    }

    /**
     * This method return Multiplied value of two numbers
     * @author Nash
     * @param numberOne - this is number One
     * @param numberTwo - this is number  Two
     * @return numberOne / numberTwo
     */
    fun testCaseDiv(numberOne: Double, numberTwo: Double): Double? {

            if (numberTwo == 0.0) {
                //throw ArithmeticException()
                 return null
            }

        return numberOne / numberTwo
    }

}

