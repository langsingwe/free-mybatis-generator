## free-mybatis-generator

### 主要功能
* 选择数据库表，逆向生成相关代码

### 使用方法
1. 导入工程

2. 配置数据库信息：编辑`resouce`目录下的配置文件`jdbc.properties`

3. 编辑相关配置项：编辑`resouce`目录下的配置文件`config.properties` 
* `database.type`：数据库类型，可选`mysql`、`oracle`，默认`mysql`
* `package`：前置包名，如填入`com.test`，那么生成的代码对应文件夹为`com.test.entity`、`com.test.controller`等
* `author`、`email`：生成类注释模板里的`@author`、`email`
* `tablePrefix`：表前缀过滤。生成的java类名，自动过滤配置项的值。可配置多个，使用英文逗号分割
* `camelCase`：是否开启驼峰命名生成类。默认开启

### 更新日志

#### 2021-03-30 v1.0.2
* 修复生成的xml文件jdbcType类型不匹配问题

#### 2021-03-24 v1.0.1
* config.properties增加配置，是否在xml生成基本的crud代码，默认开启
* 在mapper生成对应的crud接口

#### 2020-11-27 v1.0.0
* 支持`mysql`数据库导出