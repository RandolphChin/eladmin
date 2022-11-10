本项目基于[rayson517](https://gitee.com/rayson517/eladmin-plus)的eladmin mybatis-plus版本
### 功能修改
>定时任务更换成 quartz，原系统定时任务在集群环境下定时任务会执行两次
>Websocket由@ServerEndpoint注解更换为使用STOMP(Simple Text Oriented Messaging Protoco)
>添加minio对象存储

前端项目见 eladmin-ui 模块

**原项目地址：**  [https://github.com/elunez/eladmin](https://github.com/elunez/eladmin)
### springboot版本升级 CORS 跨域
Spring Boot Starter Parent >= 2.4.0 版本 corsfilter配置有改动
```
 @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Spring Boot Starter Parent < 2.4.0 使用如下
        // config.addAllowedOrigin("*");
        // Spring Boot Starter Parent >=2.4.0 使用如下
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
```
Websocket配置 STOMP 端点时跨域配置同样更改
```
@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*
		 * 1. 将 /api/my-device-app 路径注册为STOMP的端点，
		 *    用户连接了这个端点后就可以进行websocket通讯，支持socketJs
		 * 2. setAllowedOriginPatterns("*")表示可以跨域
		 * 3. withSockJS()表示支持socktJS访问
		 * 4. addInterceptors 添加自定义拦截器，这个拦截器是上一个demo自己定义的获取httpsession的拦截器
		 * 5. addInterceptors 添加拦截处理，这里MyPrincipalHandshakeHandler 封装的认证用户信息
		 */

		registry
				.addEndpoint("/api/my-device-app")
				// Spring Boot Starter Parent < 2.4.0 使用如下
				// .setAllowedOrigins("*")
				// Spring Boot Starter Parent >=2.4.0 使用如下
				.setAllowedOriginPatterns("*")
				.withSockJS();
	}
```
### Swagger
>http://localhost:8001/swagger-ui.html
#### FIND_IN_SET
字段内容用逗号分隔,关联另一个表查询并显示
```
FIND_IN_SET(str,strList)

    str 要查询的字符串
    strList 字段名，参数以“,”分隔，如(1,2,6,8)
    查询字段(strList)中包含的结果，返回结果null或记录。
```
主表
```
|  id   | attach  |
|  ----  | ----  |
| 1  | 54,53 |
| 2  | 59,54,53 |
```
附件表
```
|  storage_id   | path  |
|  ----  | ----  |
| 54  | certificate/c-20220828102428617.png |
| 53  | certificate/b-20220828102428617.png |
| 59  | certificate/a-20220828102428617.png |
```
```
SELECT
	pc.*,
	GROUP_CONCAT( s.path ) AS attachPath 
FROM
	base_person_certific pc
	LEFT JOIN tool_local_storage s ON FIND_IN_SET( s.storage_id, pc.attach ) 
GROUP BY
	pc.id
```
### quartz
定时任务更改为魂支持群集(quartz cluster)

Quartz集群依赖于数据库，所以必须首先创建Quartz数据库表(11张表) Quartz任务调度的核心元素为：Scheduler——任务调度器、Trigger——触发器、Job——任务。 trigger和job是任务调度的元数据，scheduler是实际执行调度的控制器

Trigger是用于定义调度时间的元素，即按照什么时间规则去执行任务。Quartz中主要提供了四种类型的trigger：SimpleTrigger，CronTirgger，DateIntervalTrigger，和NthIncludedDayTrigger Job用于表示被调度的任务。主要有两种类型的job：无状态的（stateless）和有状态的（stateful）。对于同一个trigger来说，有状态的job不能被并行执行 一个job可以被多个trigger关联，但是一个trigger只能关联一个job
```
调度器状态表（QRTZ_SCHEDULER_STATE）
　　说明：集群中节点实例信息，Quartz定时读取该表的信息判断集群中每个实例的当前状态。

　　instance_name：配置文件中org.quartz.scheduler.instanceId配置的名字，如果设置为AUTO,quartz会根据物理机名和当前时间产生一个名字。

　　last_checkin_time：上次检入时间

　　checkin_interval：检入间隔时间

触发器与任务关联表（qrtz_fired_triggers）
　　存储与已触发的Trigger相关的状态信息，以及相联Job的执行信息。

触发器信息表（qrtz_triggers)
　　trigger_name：trigger的名字,该名字用户自己可以随意定制,无强行要求

　　trigger_group：trigger所属组的名字,该名字用户自己随意定制,无强行要求

　　job_name：qrtz_job_details表job_name的外键

　　job_group：qrtz_job_details表job_group的外键

　　trigger_state：当前trigger状态设置为ACQUIRED,如果设为WAITING,则job不会触发

　　trigger_cron：触发器类型,使用cron表达式

任务详细信息表（qrtz_job_details）
　　说明：保存job详细信息,该表需要用户根据实际情况初始化

　　job_name：集群中job的名字,该名字用户自己可以随意定制,无强行要求。

　　job_group：集群中job的所属组的名字,该名字用户自己随意定制,无强行要求。

　　job_class_name：集群中job实现类的完全包名,quartz就是根据这个路径到classpath找到该job类的。

　　is_durable：是否持久化,把该属性设置为1，quartz会把job持久化到数据库中

　　job_data：一个blob字段，存放持久化job对象。
```
todo  QueryHelpMybatisPlus.java 中 判断 Query.Type.IS_NULL  或者 Query.Type.NOT_NULL 需要优化，首先要更改 dept的 list方法
