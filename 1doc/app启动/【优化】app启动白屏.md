###app启动白屏
   【原因】 activity启动时，在app进程创建之前，activitystack会call showStartingWindow，就是一个白色屏幕
   【优化方案】 在app启动的activity设置一个自定义主题
      <!--appStartTheme Theme-->
    <style name="appStartTheme" parent="AppTheme">
        <item name="android:windowIsTranslucent">true</item>  <!-- 方案1 透明背景 -->
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowBackground">@drawable/bg</item> <!-- 方案2 添加一个背景图片 -->
        <item name="android:windowFullscreen">true</item>
        <item name="android:windowDisablePreview">true</item>  <!-- 方案1  或者 不显示preview -->
    </style>
    1)将背景设置为透明或者不显示preview    
    【缺点】给人程序启动慢感觉，界面一次性刷出来，刷新同步
    2)给启动页添加一个背景图片
    【缺点】程序启动快，界面先显示背景图，然后再刷新其他界面控件。给人刷新不同步感觉