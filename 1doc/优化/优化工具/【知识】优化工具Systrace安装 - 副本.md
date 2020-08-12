###【知识】优化工具Systrace安装
   下载Python 2.7.16 并安装
   确认有F:\Python27\Scripts 下的pip.exe
   将python27 安装路径设置到环境变量 F:\Python27 和 F:\Python27\Scripts
   运行 systrace.py -l  测试
   如果显示如下 表明安装成功
           gfx - Graphics
       input - Input
        view - View System
     webview - WebView
          wm - Window Manager
          am - Activity Manager
          sm - Sync Manager
       audio - Audio
       video - Video
      camera - Camera
         hal - Hardware Modules
         app - Application
         res - Resource Loading
      dalvik - Dalvik VM
          rs - RenderScript
      bionic - Bionic C Library
       power - Power Management
          pm - Package Manager
          ss - System Server
    database - Database
       sched - CPU Scheduling
        freq - CPU Frequency
        idle - CPU Idle
        disk - Disk I/O
        load - CPU Load
  memreclaim - Kernel Memory Reclaim
  binder_driver - Binder Kernel driver
  binder_lock - Binder global lock trace

NOTE: more categories may be available with adb root

如果现实 缺包
     例如： No module named six   No module named win32con
     则需要到 https://pypi.org/ 去下载对应包
     pywin32-228-cp27-cp27m-win_amd64.whl
     six-1.15.0-py2.py3-none-any.whl
     下载完成以后 运行 pip install xxxx.whl 安装对应包
	