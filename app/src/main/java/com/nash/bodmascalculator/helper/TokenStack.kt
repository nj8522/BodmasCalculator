package com.nash.bodmascalculator.helper



open class TokenStack {

    private val valuesFromToken : MutableList<String> = arrayListOf()

    /**
     * This function is used to add a value into the valuesFromToken
     * @author Nash Jacob John
     */
    fun push(value : String) : Boolean{
        valuesFromToken.add(value.trim())
        return  true
    }

    /**
     * This function is used to remove the top value from the valuesFromToken
     * @author Nash Jacob John
     */
    fun pop() : Boolean{
        valuesFromToken.removeAt(valuesFromToken.size - 1)
        return  true
    }

    /**
    * This function is used to show the top value from the valuesFromToken
    * @author Nash Jacob John
     * @return top value as String
    */
    fun top() : String{
        return  valuesFromToken.last()
    }

    /**
     * This function is used to check if the valuesFromToken is empty.
     * @author Nash Jacob John
     * @return true - if its is empty
     * @return false - if its not empty
     */
    fun isEmpty() : Boolean{
        if(valuesFromToken.size == 0){
            return true
        }
        return  false
    }

    /**
     * This function returns the total length of the list(valuesFromToken).
     * @author Nash Jacob John
     * @return total size of the list
     */
    fun totalLength() : Int{
        return  valuesFromToken.size
    }

    /**
     * This function removes all the elements present inside the list(valuesFromToken).
     * @author Nash Jacob John
     */
    fun removeAll(){
        valuesFromToken.removeAll(valuesFromToken)
    }


}