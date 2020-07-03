###app启动步骤
  加载启动App
  App启动后立即展示出一个空白window
  创建App的进程
  创建Application对象
  启动main Thread
  创建启动的Activity对象
  加载View
  布置屏幕
  进行第一次绘制
  完成第一次绘制以后用MainActivity替换已经展示的Activity background window
  【常见问题】
  部分的数据库，I/O操作发生在MainActivity主线程
  Application中创建了线程池
  Application中做了大量的初始化操作
  MainActivity网络请求密集
  工作线程使用没设置优先级
  信息没缓存，重复获取同样信息
  不合理的业务流程
  废弃的老代码