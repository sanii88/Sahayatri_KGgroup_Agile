package com.sunny.sahayatribookingsewa.util

import android.util.Log

class SavedData {
    companion object{
        var mySavedData:MutableMap<String,String> = mutableMapOf<String,String>()
        fun setData(type:String,value:String){
            Log.i("MY", "Your saved Data ${mySavedData[type]} is:-->$value")
            mySavedData[type]=value
        }

        fun getSavedData(type:String):String{
            Log.i("Tag", "Your saved Data $type is:-->${mySavedData[type]}")
            return mySavedData[type]!!
        }
    }
}