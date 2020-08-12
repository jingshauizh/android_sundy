###UI绘制优化
####过度绘制优化（主要减少GPU工作量）
	查看方法
	开发者选项-》Profile GPU rendering/调试GPU过度绘制

	优化方案
	1.减少背景重复
	注意主题中的background设置：
		去掉单个activity的主题设置的属性
		可以在setContentView之前getWindow().setBackgroundDrawable(null);
		去掉所有activity主题设置中的属性
		直接在styles.xml中设置<item name="android:windowBackground">@null</item>
	非业务需要，不要去设置背景
	2.使用裁减减少控件之间的重合部分
	Android7.0之后系统做出的优化 invalidate()不再执行测量和布局动作


####布局的优化（主要减少CPU工作量）
	常用工具：
	Android/sdk/tools/bin/ui    automator   viewer.bat
	Android\sdk\tools\monitor.bat

	Device Monitor窗口中Hierarchy view：
	三个点也是代表着View的Measure, Layout和Draw。
	绿: 表示该View的此项性能比该View Tree中超过50%的View都要快；例如,代表Measure的是绿点,意味着这个视图的测量时间快于树中的视图对象的50%。
	黄: 表示该View的此项性能比该View Tree中超过50%的View都要慢； 
	红: 表示该View的此项性能是View Tree中最慢的；。

	注意点：
	1.能在一个平面显示的内容，尽量只用一个容器
	2.尽可能把相同的容器合并merge
	3.能复用的代码，用include处理，可以减少GPU重复工作
