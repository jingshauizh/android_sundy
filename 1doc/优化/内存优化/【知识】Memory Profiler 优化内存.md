### Memory Profiler 
	使用 Memory Profiler 查看 Java 堆和内存分配
	https://developer.android.google.cn/studio/profile/memory-profiler.html

	1 直接使用profile 查看内存使用情况
	2 查看内存是否抖动，可以dump 内存，dump下所有的内存对象
	3 可以record 记录 开始时间 结束时间之间的 内存变化情况
	4  分析内存泄漏，则使用 dump 然后查看 内存对象 是否有不应该存在的，
	###在您的堆转储中，请注意由下列任意情况引起的内存泄露：
	长时间引用 Activity、Context、View、Drawable 和其他对象，可能会保持对 Activity 或 Context 容器的引用。
	可以保持 Activity 实例的非静态内部类，如 Runnable。
	对象保持时间比所需时间长的缓存。
	###分析内存的技巧
	使用 Memory Profiler 时，您应对应用代码施加压力并尝试强制内存泄露。在应用中引发内存泄露的一种方式是，先让其运行一段时间，然后再检查堆。泄露在堆中可能逐渐汇聚到分配顶部。不过，泄露越小，为了看到泄露而需要运行应用的时间就越长。
	您还可以通过以下某种方式来触发内存泄露：
	在不同的 Activity 状态下，先将设备从纵向旋转为横向，再将其旋转回来，这样反复旋转多次。旋转设备经常会使应用泄露 Activity、Context 或 View 对象，因为系统会重新创建 Activity，而如果您的应用在其他地方保持对这些对象其中一个的引用，系统将无法对其进行垃圾回收。
	在不同的 Activity 状态下，在您的应用与其他应用之间切换（导航到主屏幕，然后返回到您的应用）。