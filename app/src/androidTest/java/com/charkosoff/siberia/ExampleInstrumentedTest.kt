package com.charkosoff.siberia

import android.text.TextUtils.replace
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun toCamelCase(str:String):String{
        var string = str
        for(i in string.indices){
            if (string[i]=='_' || string[i]=='-' && i<=string.length-1){
                if (string[i]!='['|| string[i]!=']' && (i+1)<=string.length-1){
                    string = string[i+1].toString().replace(string[i+1].toString(), string[i+1].toString().capitalize())
                }
                else if((i+2)<=string.length-1){
                    string = string[i+2].toString().replace(string[i+2].toString(), string[i+2].toString().capitalize())
                }
            }
        }
        string = string.replace("-", "", false)
        string = string.replace("_", "", false)
        return string
    }
}