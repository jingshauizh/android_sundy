package jings.ex.android.com.kotlin


import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.test_button

class MainActivity : BaseActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    test_button.setOnClickListener {
      println("btn clecked")
      val mPerson: Person = Person("Tom")
      printLog(mPerson.name)
      fun printDouble(d: Double) {
        print(d)
        printLog(d.toString())
      }

      val i = 1
      printDouble(i.toDouble())
      val d = 7 / 3
      Log.v(LOG_TAG, "d= " + d)

      val list = arrayOf("444", "555", "666")
      printLog("list.size= " + list.size)
      printLog("list.1= " + list.get(1))
      printLog("list.0= " + list.get(0))
      var h = 0;
      for (c in list.get(0)) {
        h++
        Log.v(LOG_TAG, "i = " + h)
        Log.v(LOG_TAG, "list.0=  " + c)
      }

      val x = 7
      for (b in 1..21) {
        Log.v(LOG_TAG, " b=  " + b)
        when {
          b > 8 -> Log.v(LOG_TAG, "list. b>8  ")
          b < 6 -> Log.v(LOG_TAG, "list. b<6  ")
          else -> Log.v(LOG_TAG, "list. x>=6   X<=8  ")

        }
      }
      testList()
      testMap()
      InitOrderDemo("mike")
    }
  }

  fun testList() {
    val numbers = setOf(1, 2, 3, 4)
    printLog("Number	of	elements:	${numbers.size}")
    if (numbers.contains(1)) printLog("1	is	in	the	set")
    val numbersBackwards = setOf(4, 3, 2, 1)
    printLog("The	sets	are	equal:	${numbers == numbersBackwards}")
  }

  fun testMap() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    var mapt = mapOf("one" to 111, "two" to 222, "three" to 444);
    printLog("mapt all keys: ${mapt.keys}")
    printLog("All	keys:	${numbersMap.keys}")
    printLog("All	values:	${numbersMap.values}")
    if ("key2" in numbersMap) printLog("Value	by	key	\"key2\":	${numbersMap["key2"]}")
    if (1 in numbersMap.values) printLog("The	value	1	is	in	the	map")
    if (numbersMap.containsValue(1)) printLog("The	value	1	is	in	the	map")  //	同上


    val	numbersIterator	=	mapt.iterator()
    while	(numbersIterator.hasNext())	{
      printLog(numbersIterator.next().value.toString())
    }
  }
}
