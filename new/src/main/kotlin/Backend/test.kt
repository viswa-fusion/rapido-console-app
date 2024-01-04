package Backend

import library.customEnum.LocalRegex

fun main(){
    while(true){
        //println(LocalRegex.NAME_PATTERN.code.matches(readln())
//        val n:Long= InputHandler.getLong(10)
//        println(n)
//        println(InputHandler.getString(2,7))
        regexTest(LocalRegex.AADHAAR_NUMBER_PATTERN)
    }
}

fun regexTest(regex:LocalRegex){
    println(regex.code.matches(readln()))
}