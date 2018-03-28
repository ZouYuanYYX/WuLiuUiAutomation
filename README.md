#web、app界面自动化测试

关键字驱动+selenium+appium+java+TestNG，使用excel管理测试用例，excel中的主sheet页控制测试用例是否需要运行，其他sheet页用来填写测试步骤。web端支持谷歌、ie、火狐浏览器，app端仅支持安卓。
##测试用例表头字段解释：
1、测试用例表存放位置：

	src\main\resources\excelTestCase\testdata.xlsx

2、主表表头解释：

	测试用例：必填
	描述：可随便写
	测试步骤表名称：必填
	测试用例是否执行：必填
	运行结果：运行代码的时候，需要把excel关闭，否则无法将测试结果写入excel（因为excel被占用了）；
3、测试步骤表表头解释：

	suiteCaseId：主表测试用例id，必填
	testCaseId：	测试步骤id，必填
	testDevices：测试端，web或app端，必填
	product：  测试产品（货主,货运站,车主,司机,小二,水泥电商,其他），必填
	function： 功能，可随便写点啥
	operation：操作，可随便写点啥
	keyWords： 关键字，必填
	keyWordsFunction：关键字方法	，必填
	value1：	需要传入的值1（只能输入字符串，不能输入数据）
	value2：	需要传入的值2   
	value3：	需要传入的值3
	value4：	需要传入的值4
	elementLocation1：元素定位表达式1（格式为：id,kw）
	elementLocation2：元素定位表达式2
	results：运行结果

##关键字解释：
	浏览器 ：选择什么浏览器，需要传入参数value1，填写要打开的浏览器（目前支持：chrome、ie、firefox）;
	打  开 ：打开url，需要传入参数value1，填写url地址;
	打开app：输入需要打开的app;
	关   闭：关闭浏览器
	关闭app： 关闭app
	关闭窗口：关闭浏览器窗口，需要传入参数value1，填写的数字表示关闭几个窗口，从右到左依次关闭；
	切换窗口：需要传入参数value1，填写的数字表示切换至第几个窗口；
	断   言：需要传入参数value1，断言页面上是否会出现该值；
	系统提示：web端页面弹出确定、取消等按钮时，用这个;
	等    待：需要传入参数value1，填写等待的时间，如：2000表示等待2s；
	输    入：需要传入参数value1，填写要输入的值；
	清    空：清空；
	单    击：单击；
	双    击：双击；
	滑动至元素再单击：单击某个元素时，如果提示：元素不可点击，则可以尝试该方法；
	javaScript单击：按钮被弹层挡住时，可用该方法单击元素
	切出iframe：切出iframe

##测试用例的编写：
### 1、web端
	
* driver驱动需要放置在：src\main\resources\drivers目录下；
* 一次单击或输入都是一个步骤，需要在excel测试步骤表中添加一行步骤；


### 2、app端
* app测试包需要放置在：apps目录下；
* app测试包名称、安卓设备名称等配置信息：src\main\resources\properties\excel_and_app_config.properties，配置信息如下：
		
		要安装的app包名:
	
		shipperPackageName = huoYunZhan-release.apk	
		carrierPackageName = hongshiwuliu_2.7.0.apk
		
		appium服务需要占用的端口号:

		shipperPort= 4723
		carrierPort= 4725
		
		要连接的设备名:

		shipperDeviceName = 127.0.0.1:62001
		carrierDeviceName = 127.0.0.1:6555
		
		appium日志地址:

		appiumShipperLogPath = F:/UiAutomationFile/logs/Appium_shipper.log
		appiumCarrierLogPath = F:/UiAutomationFile/logs/Appium_carrier.log
		
		要连接的URL地址:

		shipperUrl = http://127.0.0.1:4723/wd/hub
		carrierUrl = http://127.0.0.1:4725/wd/hub
		
		要测试的安卓版本:

		shipperVersion= 4.4.2
		carrierVersion= 4.4.4

##截图：

运行界面自动化代码后，每跑一个步骤都会做截图动作，截图保存的路径在配置文件中配置：src\main\resources\properties\excel_and_app_config.properties

	测试截图保存的路径
	picturePath = F:/UiAutomationFile/pictures

##日志与报告：

* 日志文件保存路径也在配置文件中配置：src\main\resources\properties\log4j.properties；

* 测试报告：report.html（该报告会记录成功、失败用例个数，并用饼状图显示）；
