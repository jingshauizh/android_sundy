// IPersonManager.aidl
package com.jingshuai.android.fregmentapp.service;

// Declare any non-default types here with import statements
import com.jingshuai.android.fregmentapp.service.Person;
interface IPersonManager {
    List<Person> getPersonList();
    //关于这个参数in 其实你不加也是可以编译通过的，这里我就先加一下 具体参数的意义 以后会说
    void addPerson(in Person person);
}
