#  我的论坛项目
### 仿码问社区论坛项目
### 字段名中存在“_",驼峰问题
字段名中存在“_"，mybatis没有自动转换成驼峰命名
需要在application.properties中配置设置为true,设置如下:
mybatis.configuration.map-underscore-to-camel-case=true
#  1、什么是springboot  

 SpringBoot是Spring项目中的一个子工程，与我们所熟知的Spring-framework 同属于spring的产品，是用来简化 spring 初始搭建和开发过程使用特定的方式进行配置，创建了独立的 spring 引用程序 main 方法运行。同时SpringBoot中镶入了 Tomcat 无需部署 war 包直接打成 jar 包 nohup java -jar – & 启动就好，简化了 maven 的配置，自动配置 spring 添加对应的 starter 自动化配置。
   Spring Boot浅谈(是什么/能干什么/优点和不足)
   [https://blog.csdn.net/a_blackmoon/article/details/85100745](https://blog.csdn.net/a_blackmoon/article/details/85100745)
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111154822369.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODQxMzUy,size_16,color_FFFFFF,t_70#pic_center)

#   2、如何创建springboot

#### 1）使用idea快速创建，spring的官方文档：
[https://spring.io/quickstart](https://spring.io/quickstart)
注解：springboot它内嵌tomcat、自带了默认数据库连接池是Hikari，
#### 2）[参考Spring Boot 集成教程](https://www.qikegu.com/docs/2554)

# 3、需要注意什么，它是如何实现前后端数据的交互的？

#### 1）springboot默认扫描包的范围为启动类所在包和子包，不包括第三方的jar包，
扫描资源路径：添加templates模板时，默认扫描到resources下static（资源文件）、以及template（这个下面放html文件），所以在引用js或css等资源文件需要注意路径，为当前根目录“/”就行了；而在resources下新建的mapper文件（xml）需要在配置文件application.yml中配置路径（这个可以参考[springboot与mybatis整合](https://blog.csdn.net/iku5200/article/details/82856621)）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201111160309293.png#pic_center)

#### 2）springboot中的一些注解，比如：@SpringBootApplication、@MapperScan、@ComponentScan、@GetMapping、 @RequestMapping、 @Autowired、@Component、@Mapper、@RequestParam、@RequestBody、@ResponseBody、@PathVariable、等注解的用法，
自己多理解，理清他们的用法。
#### 3）如何实现前后端数据的交互的呢？这个就比较重要
一般写一个html页面就要写对应的Controller请求，用来写访问请求的url，model
![当然这只是一种方式，还有其他的方式，比如用ModelAndView在这里插入图片描述](https://img-blog.csdnimg.cn/20201111163244492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODQxMzUy,size_16,color_FFFFFF,t_70#pic_center)
当然这只是一种方式，还有其他的方式，比如将返回String类型改为返回ModelAndView，后面return new ModelAndView("hello")就行了
使用这种的话，可以使用model来实现数据的传递，前端直接用th表达式获取（当然得在页面导入th标签文件引用才行）

#### 4）还可以使用json格式进行数据，这种也是目前最流行的方式。使用list集合框架打包对象，再使用map方式将list添加，并返回给前端，
 需要注意；要加@ResponseBody注解，表明返回为json格式。

#  4、做完这个项目你学到了什么

#### 1、了解了springboot的基本实现过程，对springboot有了一个初步的了解
#### 2、做完这个项目，我积累了一定的项目经验及如何学习查找资料，调式项目，加强了修改bug的能力，解决错误的能力，虽然自己目前还是很菜，编程界小白，经过一个多月的学习，我还是有了一定的进步，也了解了一些东西，比如微服务等内容吧，可能是前期玩得太狠了，导致现在离他们还有一段距离，所以要加油哦
#### 3、遇到错误(bug),首先自己查找错误原因，尝试着解读、分析错误（实在翻译不了，可以用有道字典翻译错误原因），尝试着定位错误，debug调式，再解决不了，就查询百度，多翻阅，耐下心来查找，多尝试，在改bug过程中，一定要冷静下来，静下心来，拥有耐心，一点一点的分析，加油；若这样还没有解决，在询问大佬，（态度要诚恳，表明自己查询过多篇资料，调式等）；解决之后将bug内容写在笔记里，（抽空余时间思考，为什么这样会出现bug，学会透过现象看本质，以及分析为啥自己没有解决，是思路，还是知识，然后继续加油吧）
#### 4、适当学会一下大佬们的业务逻辑分析，他们是怎么写代码，怎么梳理该功能的逻辑的，这个很重要，要学会慢慢分析，以及如何理清该功能的逻辑路线、、、

#   5、本项目的关键步骤

#### 1、首先要理解本项目的基本业务流程、基本功能
这个项目是干什么，作用是什么，为什么要写这个项目，以及这个项目要实现哪些功能，大体可分为哪些模块，基本流程是怎么样的，以及打算用什么技术实现等，如果让你去做，你会怎么做（当时自己做的时候是没有了解，毕竟技术不够，做本项目主要是了解springboot，后面可以适当分析）
#### 2、搭建了基本环境，springboot项目的基本环境，（当然本项目的那个大佬有些地方没有做好，他的数据库环境这块刚开始没有搭，不过人家技术还是可以的，现场遇到bug，能够慢慢调式出来还有英文功底还不错，看英文文档不是问题，毕竟多次提到查官方文档）
#### 3、实现将代码交给GitHub管理，搭建前端页面基本框架，实现授权登录，这一块做得还是蛮久的主要是遇到了bug（坑），老得不到用户信息；我了解了授权登录的基本机制，后面要加以总结。
#### 4、将登录信息写到数据库，重新搭建了数据库，这里也遇到了坑，这个地方报了莫名其妙的错误，老是显示数据连接不上，害得我重新将项目再做了一遍，耽误了不少时间
#### 5、接着实现了提问功能，（要学会熟练运用前端框架，快速搭建前端页面，当时那个大佬照着bootstrap官方文档快速搭建起一个页面），我实现前端jquery校验（当然后端也需要校验，我嫌弃太麻烦），这里我用的是ajax异步请求，json方式数据传递，（当然那个大佬用的是form表单同步提交，采用model方式传递数据），这个地方也给我后来实现问题信息的编辑，埋了一个坑，编辑时，我数据无法回显成功，当时将修改与添加放到同一页面，待修改的数据怎么也渲染不到添加页面上，经过多方努力，在跳转需要将id传过去，再发送请求获取待修改数据。
#### 6、首页问题列表展示，实现分页，主要在写时，有时需要将一些属性封装成一个类，前端用thymeleaf中th标签，实现列表遍历及数据的显示，分页我重点理解了一下
#### 7、在完成上述功能，我还了解了cookie与session之间区别，用session保存登录的状态，（有些功能就不写了）
#### 8、实现了初步的拦截器，对异常进行了处理等功能，还是实现日志功能。

#  6、如果让你写这个项目，我会怎么做？

#### 1、首先理清项目需求，分析出项目的相关需求，查阅相关资料，做的是论坛项目，分析出项目的基本功能
发布问题功能，评论问题功能，展示问题详细信息的功能，通知功能，展示问题列表，搜索问题功能、登录功能等
#### 2、设计好数据库，搭建项目基本环境
#### 3、登录功能修改，既可以授权登录又可以注册登录

