#  我的论坛项目
### 字段名中存在“_",驼峰问题
字段名中存在“_"，mybatis没有自动转换成驼峰命名
需要在application.properties中配置设置为true,设置如下:
mybatis.configuration.map-underscore-to-camel-case=true

