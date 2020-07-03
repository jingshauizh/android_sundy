###app启动命令
  如何对比启动时间
  adb shell am start -W packagename/MainActivity
  adb shell am start -S -R 10 -W packagename/.MainActivity
  -S表示每次启动前先强行停止
  -R表示重复测试次数