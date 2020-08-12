###app启动加速的优化方向
  【原因】 避免IO操作
        系列化反序列化
        网络操作
        布局嵌套
  【解决方案】
    四个维度：
      必要且耗时：启动初始化，考虑用线程来初始化
      必要不耗时：首页绘制
      非必要耗时：数据上报，插件初始化【用到的时候在初始化，或者空闲时初始化】
      非必要不耗时：直接去掉，在需要用的时候再加载，比如其他第三方组件的初始化
    思考方向
      分步加载，以大化小，优先级高的放前
      异步加载，耗时多的异步化
      延期加载，非必要的数据延时加载

    【通用启动优化方案】
    利用主题快速显示界面
    异步初始化组件
    通过梳理业务逻辑，延迟初始化组件、操作
    正确使用线程
    去掉无用代码、重复逻辑等
####启动耗时测试
	在app的oncreate 方法中开始添加 
	Debug.startMethodTracing(filepath);
	结束的地方添加：
	Debug.stopMethodTracing();

	2 也可以在log 里面用 Displayed 过滤查看activity的启动时间
	4.4版本以后Logcat 输入Display筛选系统日志  不过滤信息No Filters

####黑白屏问题
	解决办法：
	1.在自己的<style name="AppTheme" parent="Theme.AppCompat.Light">中加入windowsbackground
	2.设置windowbackground为透明的  <item name="android:windowIsTranslucent">true</item>
	但这2种方法会有个问题，所有的activity启动都会显示
	3.单独做成一个主题
	<style name="AppTheme.Launcher">
	        <item name="android:windowBackground">@drawable/bg</item>
	    </style>
	    <style name="AppTheme.Launcher1">
	        <item name="android:windowBackground">@drawable/bg</item>
	    </style>
	    <style name="AppTheme.Launcher2">
	        <item name="android:windowBackground">@drawable/bg</item>
	    </style>
	再在功能清单中的单独activity下设置
	<activity
	            android:theme="@style/AppTheme.Launcher"
	然后在程序中使用setTheme(R.style.AppTheme);
	让APP中所有的activity还是使用以前的样式，这样做就只有启动时才使用自己的样式

	
