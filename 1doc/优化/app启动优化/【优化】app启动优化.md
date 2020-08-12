###app启动优化
####启动方式
	1 冷启动：有台没有应用进程，需要创建进程并分配给该应用
	2 热启动：后台已经有该应用的进程，比如home键，会从已有的进程中启动
	3 温启动：启动应用时后台已经有了该应用的进程，但是启动的入口activity被干掉了，比如back键
			 应用虽然退出了，但是应用进程还保留，再次启动叫温启动

####性能检查方向
	application的构造方法-->
	attachbasecontext-->
	appOcrate-->
	Activity的构造方法-->
	onCreate-->
	配置主题中背景属性-->
	ONstart-->
	OnResume-->
	测量，布局，绘制显示在桌面上

####App 加载任务优先级
	优先级为1  启动加载，如必要的组件一定要在主线程中立即初始化(入口 Activity 可能立即会用到)
	优先级为2  首页渲染完成后 延迟加载，组件一定要在主线程中初始化，但是可以延迟初始化。
	优先级为3  首页渲染后 延迟加载，组件可以在子线程中初始化。

	分阶段启动 
	https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650403370&idx=1&sn=b4297b138eb7f73c95a6279c3458f025&chksm=83953a32b4e2b3247fc18cbee08a2682d8b09720a1c5fef0c36257ae92b1e201cb1ad3125455&mpshare=1&scene=1&srcid=#rd
	stage1 app运行基础加载 完成app必须基础库的任务初始化，等待并发任务完成
	stage2 UI运行基础加载，完成页面所需中间件的任务初始化，等待并发任务完成
	stage3 资源预加载  完成一些 不重要也不紧急 的任务初始化，使得有些组件 有可能 被提前初始化，提高页面加载速度 重在 预加载 提升用户体验
	stage4 按需加载  完成一些 特定条件下 的 任务初始化

####瓶颈查找办法
	使用traceview 找出单个方法执行时间长的
	找出执行次数多的

####优化思路总结
	UI渲染，去除重复绘制
	根据优先级划分初始化工作
	Sharepreference 优化
	网络错误优化，
	使用ViewStub的方式，减少UI一次性绘制的压力。 
	Multidex的使用，也是拖慢启动速度的元凶，必须要做优化
	检查BaseActivity，不恰当的操作会影响所有子Activity的启动。 
	设置线程的优先级，不与主线程抢资源

####启动加速的优化方向
	1 利用提前展示的window，快速展示一个画面，给用户一个反馈快速的体验
		设置背景theme
		把背景样式设置为透明：会导致程序启动慢的错觉
	2 避免在启动时做密集沉重的初始化
		四个维度：
			必要且耗时：如启动初始化，考虑用线程来初始化
			必要不耗时：如首页绘制
			非必要耗时：如数据上报，插件初始化
			非必要不耗时：直接去掉，在需要用的时候再加载： 如其他第三方组件的初始化
		思考方向
			分步加载：以大化小，优先级高的放前
			异步加载：耗时多的异步化
			延期加载：非必要的数据延时加载

	3 定位问题
		避免I/O操作
		序列化/反序列化
		网络操作
		布局嵌套	

####通用启动优化方案
	利用主题快速显示界面
	异步初始化组件
	通过梳理业务逻辑，延迟初始化组件、操作
	正确使用线程，设置线程的优先级，不与主线程抢资源
	去掉无用代码、重复逻辑等	
#####线程优化
	控制线程数量 – 线程池
	检查线程间的锁 ，防止依赖等待
	使用合理的启动架构
		微信内部使用的 mmkernel
		阿里 Alpha

####启动优化常见问题
	部分的数据库，I/O操作发生在MainActivity主线程
	Application中创建了线程池
	Application中做了大量的初始化操作
	MainActivity网络请求密集
	工作线程使用没设置优先级
	信息没缓存，重复获取同样信息
	不合理的业务流程
	废弃的老代码
	Bitmap 大图片或者 VectorDrawable加载

####其他
	1 放在子线程的组件初始化建议延迟初始化 ，这样就可以了解是否会对项目造成影响！
	将Bugly，x5内核初始化，SP的读写，友盟等组件放到子线程中初始化。（子线程初始化不能影响到组件的使用）
        new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程的优先级，不与主线程抢资源
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
				//子线程初始化第三方组件
				Thread.sleep(5000);//建议延迟初始化，可以发现是否影响其它功能，或者是崩溃！
            }
        }).start();

    2 将需要在主线程中初始化但是可以不用立即完成的动作延迟加载
    （原本是想在入口 Activity 中进行此项操作，不过组件的初始化放在 Application 中统一管理为妙.）
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
				//延迟初始化组件
            }
        }, 3000);



####系统调度优化
	https://zhuanlan.zhihu.com/p/92497570
	应用启动的时候，如果主线程的工作过多，也会造成主线程过于繁忙，下面几个系统调度相关的点需要注意：

	1 启动过程中减少系统调用，避免与 AMS、WMS 竞争锁。启动过程中本身 AMS 和 WMS 的工作就很多，
	且 AMS 和 WMS 很多操作都是带锁的，如果此时 App 再有过多的 Binder 调用与 AMS、WMS 通信，
	SystemServer 就会出现大量的锁等待，阻塞关键操作
	2 启动过程中不要启动子进程，如果好几个进程同时启动，系统负担则会加倍，SystemServer 也会更繁忙
	3 启动过程中除了 Activity 之外的组件启动要谨慎，因为四大组件的启动都是在主线程的，如果组件启动慢，
	占用了 Message 通道，也会影响应用的启动速度
	4 Application 和主 Activity 的 onCreate 中异步初始化某些代码

####GC 优化
	启动过程中减少 GC 的次数
		避免进行大量的字符串操作，特别是序列化和反序列化
		频繁创建的对象需要考虑复用
		转移到 Native 实现
		支付宝客户端架构解析：Android 客户端启动速度优化之「垃圾回收」
		https://juejin.im/post/5be1077d518825171140dbfa

####IO 优化
	启动过程中负载比较高，有许多系统 IO 都在此时发生，这时候 IO 的性能下降会比较快，
	此时 App 中的 IO 操作会比平时更慢一些，尤其是在性能比较差的机器上。
	IO 分网络 IO 和磁盘 IO ，启动过程中不建议进行网络 IO ，对于磁盘 IO 则要细扣，邵文在高手课里面有讲到：
	1 我们要清楚启动过程中读了什么文件、多少个字节、 Buffer 是多大，使用了多长时间、在什么线程等一系列信息
	2 进行启动过程中的 IO 监控，微信在监控 IO 时发现有用户的 db 文件达到了 500MB

####资源重排
	利用 Linux 的 IO 读取策略，PageCache 和 ReadAhead 机制，按照读取顺序重新排列，减少磁盘 IO 次数 。
	具体操作可以参考支付宝 App 构建优化解析：
	通过安装包重排布优化 Android 端启动性能 这篇文章https://juejin.im/post/5be400c3f265da61476fb63c

####类重排
	类重排的实现通过 ReDex 的 Interdex 调整类在 Dex 中的排列顺序。Interdex 优化不需要去分析类引用，
	它只需要调整 Dex 中类的顺序，把启动时需要加载的类按顺序放到主 dex 里，这个工作我们完全可以在编译过程中实现，
	而且这个优化可以提升启动速度，优化效果从 facebook 公布的数据来看也比较可观，性价比高。
	具体实现可以参考 Redex 初探与 Interdex：Andorid 冷启动优化 https://mp.weixin.qq.com/s/Bf41Kez_OLZTyty4EondHA?

####主页面布局优化
	应用主界面布局优化是老生常谈了，综合起来无非就是下面两点，
	这个需要结合具体的界面布局去做优化，网上也有比较多的资料可以查阅

	1 通过减少冗余或者嵌套布局来降低视图层次结构
	2 用 ViewStub 替代在启动过程中不需要显示的 UI 控件
	3 使用自定义 View 替代复杂的 View 叠加

#### 闲时调用
	IdleHandler：当 Handler 空闲的时候才会被调用，如果返回 true, 则会一直执行，如果返回 false，
	执行完一次后就会被移除消息队列。比如，我们可以将从服务器获取推送 Token 的任务放在延迟 IdleHandler 
	中执行，或者把一些不重要的 View 的加载放到 IdleHandler 中执行
####启动网络链路优化
#####问题和优化点
	发送处理阶段：网络库bindService影响前x个请求，图片并发限制图片库线程排队
	网络耗时：部分请求响应size大，包括 SO文件，Cache资源，图片原图大尺寸等
	返回处理：个别数据网关请求json串复杂解析严重耗时（3s）,且历史线程排队设计不合适
	上屏阻塞：回调UI线程被阻，反映主线程卡顿严重。高端机达1s，低端机恶化达3s以上
	回调阻塞：部分业务回调执行耗时，阻塞主线程或回调线程
#####优化
	多次重复的请求，业务方务必收敛请求次数，减少非必须请求。
	数据大的请求如资源文件、so文件，非启动必须统一延后或取消。
	业务方回调执行阻塞主线程耗时过长整改。我们知道，肉眼可见流畅运行，需要运行60帧/秒， 意味着每帧的处理时间不超过16ms。针对主线程执行回调超过16ms的业务方，推动主线程执行优化。
	协议json串过于复杂导致解析耗时严重，网络并发线程数有限，解析耗时过长意味着请求长时间占用MTOP线程影响其他关键请求执行。推动业务方handler注入使用自己的线程解析或简化json串。

####业务优化
	优化业务中的代码效率，抓大放小，先从比较明显的瓶颈处下手，逐步进行优化
	历史债务要偿还，历史的代码要重构，不能一直拖着
	具体的业务会有具体的优化场景，大家可以参考这篇文章中的优化流程和优化项(https://www.jianshu.com/p/f5514b1a826c)：

	1. 数据库及IO操作都移到工作线程，并且设置线程优先级为THREAD_PRIORITY_BACKGROUND，这样工作线程最多能获取到10%的时间片，优先保证主线程执行
	2. 流程梳理，延后执行；实际上，这一步对项目启动加速最有效果。通过流程梳理发现部分流程调用时机偏早、失误等，例如：
	** 1. 更新等操作无需在首屏尚未展示就调用，造成资源竞争**
	** 2. 调用了IOS为了规避审核而做的开关，造成网络请求密集**
	** 3. 自有统计在Application的调用里创建数量固定为5的线程池，造成资源竞争**
	** 4. traceview功能说明图中最后一行可以看到编号12执行5次，耗时排名前列；此处线程池的创建是必要但可以延后的**
	** 5. 修改广告闪屏逻辑为下次生效**
	3. 去掉无用但被执行的老代码
	4. 去掉开发阶段使用但线上被执行的代码
	5. 去掉重复逻辑执行代码
	6. 去掉调用三方SDK里或者Demo里的多余代码
	7. 信息缓存，常用信息只在第一次获取，之后从缓存中取
	8. 项目是多进程架构，只在主进程执行Application的onCreate()


####参考文章
	1.	都9102年了，Android 冷启动优化除了老三样还有哪些新招？
	https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650829097&idx=2&sn=e59841d4b1ed7e12a30e29ec51072d70&chksm=80b7a5b7b7c02ca184e0c06289d90823d589e738c55712318875f51e4aeb8646294b8d426299&mpshare=1&scene=1&srcid=&sharer_sharetime=1571275213308&sharer_shareid=60bd7acea7881a97fbf9a6126d3e88d3#rd
	2.	App startup time
	https://developer.android.google.cn/topic/performance/vitals/launch-time
	3.	历时1年，上百万行代码！首次揭秘手淘全链路性能优化（上）
	https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650403370&idx=1&sn=b4297b138eb7f73c95a6279c3458f025&chksm=83953a32b4e2b3247fc18cbee08a2682d8b09720a1c5fef0c36257ae92b1e201cb1ad3125455&mpshare=1&scene=1&srcid=#rd
	4.	极客时间 ： Android 高手开发课
	https://time.geekbang.org/column/intro/142
	5.	Facebook-Redex
	https://github.com/facebook/redex
	6.	关于 Android 异步启动框架 alpha 的思考
	7.	支付宝 App 构建优化解析：通过安装包重排布优化 Android 端启动性能
	https://mp.weixin.qq.com/s/79tAFx6zi3JRG-ewoapIVQ
	8.	Redex 官网
	https://github.com/facebook/redex
	9.	Redex 初探与 Interdex：Andorid 冷启动优化
	https://mp.weixin.qq.com/s/Bf41Kez_OLZTyty4EondHA?
	10.	Android性能优化笔记（一）——启动优化
	https://juejin.im/post/5c21ea325188254eaa5c45b1#heading-5
