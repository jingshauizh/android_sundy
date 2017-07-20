APP_MODULES := NdkTest
/*这个变量是可选的，如果没有定义，NDK将由在Android.mk中声明的默认的模块编译，并且包含所有的子文件(makefile文件)；
如果APP_MODULES定义了，它不许是一个空格分隔的模块列表，这个模块名字被定义在Android.mk文件中的LOCAL_MODULE中。
注意NDK会自动计算模块的依赖*/
APP_ABI := all//支持所有平台，也可以指定平台空格隔开armeabi armeabi-v7a x86