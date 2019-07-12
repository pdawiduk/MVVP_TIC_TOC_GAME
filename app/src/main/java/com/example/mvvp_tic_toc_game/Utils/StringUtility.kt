package com.example.mvvp_tic_toc_game.Utils

public class StringUtility {

    companion object{

        public fun stringFromNumber(vararg numbers:Int):String{

            var result:StringBuilder = StringBuilder()

            for (num in numbers)
                result.append(num)

            return  result.toString()

        }

        public fun isNullOrEmpty(value:String):Boolean{

            return value == null || value.isEmpty()
        }

    }
}