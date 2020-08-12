###app保活和拉活
####【app保活】手段之Activity提权
	监控手机锁屏解锁事件，在屏幕锁屏时启动1个像素透明的 Activity，在用户解锁时将 Activity 销毁掉，从而达到提高进程优先级的作用。
	代码实现：
	1 创建1个像素的Activity KeepActivity
	2 创建广播接收者 KeepReceiver
		if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
	    //灭屏 开启1px activity
	    KeepManager.getInstance().startKeep(context);
		} else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
			//亮屏 关闭
		    KeepManager.getInstance().finishKeep();
		}
	3 创建广播注册管理单例类KeepManager
		注册/反注册广播
		启动/关闭keepActivity
		设置keepActivity弱引用

####【app保活】手段之Service提权
	创建一个前台服务用于提高app在按下home键之后的进程优先级
	startForeground(ID,Notification)：使Service成为前台Service。 前台服务需要在通知栏显示一条通知

####【app拉活】广播拉活  *
	
	在发生特定系统事件时，系统会发出广播，通过在 AndroidManifest 中静态注册对应的广播监听器，即可在发生响应事件时拉活。
	但是从android 7.0开始，对广播进行了限制，而且在8.0更加严格

####【app拉活】“全家桶”拉活  ****
	有条件的话，全家桶尽可能要实现 这个 拉活
	有多个app在用户设备上安装，只要开启其中一个就可以将其他的app也拉活。比如手机里装了手Q、QQ空间、兴趣部落等等，那么打开任意一个app后，其他的app也都会被唤醒。

####【app拉活】Service机制(Sticky)拉活 ****
	将 Service 设置为 START_STICKY，利用系统机制在 Service 挂掉后自动拉活
	只要 targetSdkVersion 不小于5，就默认是 START_STICKY。
	但是某些ROM 系统不会拉活。并且经过测试，Service 第一次被异常杀死后很快被重启，第二次会比第一次慢，第三次又会比前一次慢，一旦在短时间内 Service 被杀死4-5次，则系统不再拉起。

####【app拉活】账户同步拉活 **
	手机系统设置里会有“帐户”一项功能，任何第三方APP都可以通过此功能将数据在一定时间内同步到服务器中去。系统在将APP帐户同步时，会将未启动的APP进程拉活

####【app拉活】JobScheduler拉活 ****
	JobScheduler允许在特定状态与特定时间间隔周期执行任务。可以利用它的这个特点完成保活的功能,效果即开启一个定时器，与普通定时器不同的是其调度由系统完成。

	注意setPeriodic方法
	在7.0以上如果设置小于15min不起作用，可以使用setMinimumLatency设置延时启动，并且轮询

####【app拉活】推送拉活 **
	根据终端不同，在小米手机（包括 MIUI）接入小米推送、华为手机接入华为推送。

####【app拉活】Native拉活 *
	Native fork子进程用于观察当前app主进程的存亡状态。对于5.0以上成功率极低。

####【app拉活】双进程守护 *****
	两个进程共同运行，如果有其中一个进程被杀，那么另外一个进程就会将被杀的进程重新拉起