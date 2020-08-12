###app组件化
####组件化配置
	1 配置 app_config.gradle
	    ext {
	    usename="Derry"
	
	    // 开发环境 / 生产环境（测试/正式）
	    isRelease = true
		}
	2 在项目gradle配置中引用 app_config.gradle 
		apply from : "app_config.gradle"

	3 isRelease 在 项目 和module 中判断 编译是module还是app
		if (isRelease) { // 如果是发布版本时，各个模块都不能独立运行
		apply plugin: 'com.android.library'
		} else {
		apply plugin: 'com.android.application'
		}

####组件路由
