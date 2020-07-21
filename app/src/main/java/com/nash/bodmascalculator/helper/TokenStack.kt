package com.nash.bodmascalculator.helper



class TokenStack {

    val valuesFromToken : MutableList<String> = arrayListOf()

    fun push(value : String) : Boolean{
        valuesFromToken.add(value.trim())
        return  true
    }

    fun pop() : Boolean{
        valuesFromToken.removeAt(valuesFromToken.size - 1)
        return  true
    }

    fun top() : String{
        return  valuesFromToken.last()
    }

    fun isEmpty() : Boolean{
        if(valuesFromToken.size == 0){
            return true
        }
        return  false
    }

    fun display(){
        for(element in valuesFromToken.indices){
            println(valuesFromToken[element])
        }
    }

    fun totalLength() : Int{
        return  valuesFromToken.size
    }

}