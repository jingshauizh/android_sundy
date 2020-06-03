package jings.ex.android.com.kotlin

import android.util.Log

/**
 * Created by jings on 2020/4/23.
 */
val LOG_TAG: String = "kotlin"
fun printLog(msg: String) {
  Log.v(LOG_TAG, msg)
}
class Person(val name:String)

class InitOrderDemo(name: String) {
  val firstProperty = "First	property:	$name".also(::println)

  init {
    printLog("First	initializer	block	that	prints	${name}")
  }

  val secondProperty = "Second	property:	${name.length}".also(::println)

  init {
    printLog("Second	initializer	block	that	prints	${name.length}")
  }
} //s