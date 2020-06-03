package jings.ex.android.com.kotlin

import android.app.Activity
import android.util.Log

/**
 * Created by jings on 2020/5/7.
 */
open class BaseActivity : Activity() {
  val LOG_TAG: String = "kotlin"
  fun printLog(msg: String) {
    Log.v(LOG_TAG, msg)
  }
}