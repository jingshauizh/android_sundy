###【知识】使用systrace的问题记录
  1. No module named win32con
缺少库文件
解决：
pip2 install pypiwin32（这里遇到个问题，通过-i 选择豆瓣源和清华大学源下载都出现了问题（下好包之后解析异常），直接下载没有问题，不知道什么情况）
我电脑里也没有需要的six库，同理下载就好

2. ValueError: Invalid trace result format for HTML output
解决方案：
https://stackoverflow.com/questions/48606877/systrace-output-error
添加选项：--no-compress
即命令为 python27 E:\Andriod_SDK\platform-tools\systrace\systrace.py -t 10 -o D:\out.html --no-compress

3. 之后又出现了一个问题
exit status: 1
output:
/system/bin/sh: can't create /sys/kernel/debug/tracing/tracing_on: Permission denied
百度了一下，据说是android4.4 和5.1会出现无法使用systrace的问题
https://blog.csdn.net/tzr0330/article/details/82024180
解决方案：
adb root # 获取adb root权限

4. 执行之后在运行一次systrace.py脚本，卡在Tracing completed. Collecting output...很久，但是最后还是跑了，结果还是报错
又回到了ValueError: Invalid trace result format for HTML output，懵逼
解决方案：
查到这个 https://stackoverflow.com/questions/48500644/systrace-invalid-trace-result-format-for-html-output （待验证)
仔细看了一下报错，中间有一行 Did you forget adb root?
怀疑是adb root 没弄好，权限问题
重新执行

    adb root
    adb remount
    adb shell mount -o remount rw /
    adb shell
还是Collecting output...等了很久，最后终于看到了
Wrote trace HTML file: file://D:\ALOG\out.html
打开浏览器把生成的文件拖进去，看了一下，应该没问题了