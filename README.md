<h1 style="text-align: center">EL-ADMIN 后台管理系统</h1>
<div style="text-align: center">

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/elunez/eladmin/blob/master/LICENSE)
[![star](https://gitee.com/elunez/eladmin/badge/star.svg?theme=white)](https://gitee.com/elunez/eladmin)
[![GitHub stars](https://img.shields.io/github/stars/elunez/eladmin.svg?style=social&label=Stars)](https://github.com/elunez/eladmin)
[![GitHub forks](https://img.shields.io/github/forks/elunez/eladmin.svg?style=social&label=Fork)](https://github.com/elunez/eladmin)

</div>

#### 项目简介
一个基于 Spring Boot 2.1.0 、 Spring Boot Jpa、 JWT、Spring Security、Redis、Vue的前后端分离的后台管理系统

**开发文档：**  [https://el-admin.vip](https://el-admin.vip)

**体验地址：**  [https://el-admin.xin](https://el-admin.xin)

**账号密码：** `admin / 123456`
#### 基于eladmin添加的功能
 1. 美团 Leaf ID生成系统
 2. 定时任务quartz支持集群
#### 增加美团Leaf模块用于生成递增序列
IDGen.get("big_tag名称")
#### 添加支持多租户(租户字段)
使用 Dept 中的 id 和 Job 中的 id 分别作为租户ID，JOB与Dept是一对多关系；
新建一角色A，指定A可以自定义访问的dept集合；
用户User可以访问一个或多个Dept，用户User只能归属于一个Job，一个Job可以有多个Dept
用户名 许昌1号可以查看到 JOB为许昌，Dept为A区的数据
用户名 许昌2号可以查看到 JOB为许昌，Dept为B区的数据
用户名 许昌可以查看到 JOB为许昌，Dept为A、B两个区的数据

如何使用：所有数据表都需要添加 job_id 和 dept_id 租户字段
示例 testing表
```
id	    bigint					
dept_id	bigint		展区			
job_id	bigint		场馆			
name	varchar		姓名	

```
```java
@Data
@DataPermission(fieldName = "id",joinName = "dept")
@DataVenuePermission(fieldName = "id",joinName = "job")
public class TestingQueryCriteria{

}

@Entity
@Data
@Table(name="testing")
public class Testing implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Long id;

    @JoinColumn(name = "dept_id",referencedColumnName = "dept_id")
    @ManyToOne
    @ApiModelProperty(value = "展区")
    private Dept dept;

    @JoinColumn(name = "job_id",referencedColumnName = "job_id")
    @ManyToOne
    @ApiModelProperty(value = "场馆")
    private Job job;

    @Column(name = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    public void copy(Testing source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
```
```sql
CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128)  NOT NULL DEFAULT '', -- your biz unique name
  `max_id` bigint(20) NOT NULL DEFAULT '1',
  `step` int(11) NOT NULL,
  `description` varchar(256)  DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB;

insert into leaf_alloc(biz_tag, max_id, step, description) values('leaf-segment-test', 1, 2000, 'Test leaf Segment Mode Get Id')
```
#### 定时任务更改为魂支持群集(quartz cluster)
Quartz集群依赖于数据库，所以必须首先创建Quartz数据库表(11张表)
Quartz任务调度的核心元素为：Scheduler——任务调度器、Trigger——触发器、Job——任务。
trigger和job是任务调度的元数据，scheduler是实际执行调度的控制器

Trigger是用于定义调度时间的元素，即按照什么时间规则去执行任务。Quartz中主要提供了四种类型的trigger：SimpleTrigger，CronTirgger，DateIntervalTrigger，和NthIncludedDayTrigger
Job用于表示被调度的任务。主要有两种类型的job：无状态的（stateless）和有状态的（stateful）。对于同一个trigger来说，有状态的job不能被并行执行
一个job可以被多个trigger关联，但是一个trigger只能关联一个job
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
### rabbitmq 部署
#### 1st make Dockerfile
```
FROM rabbitmq:3-management-alpine
RUN apk add curl
RUN curl -L https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/3.8.17/rabbitmq_delayed_message_exchange-3.8.17.8f537ac.ez> $RABBITMQ_HOME/plugins/rabbitmq_delayed_message_exchange-3.8.17.8f537ac.ez 
RUN chown rabbitmq:rabbitmq $RABBITMQ_HOME/plugins/rabbitmq_delayed_message_exchange-3.8.17.8f537ac.ez
RUN rabbitmq-plugins enable --offline rabbitmq_delayed_message_exchange
RUN rabbitmq-plugins enable --offline rabbitmq_consistent_hash_exchange
```
#### 2nd build rabbitmq
>docker build -t rabbitmq:3-management-alpine .

#### 3rd run rabbitmq
```
docker run -d --hostname rabbit --name rabbitmq -p 4369:4369 -p 5672:5672 -p 15672:15672 -p 1883:1883 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -v E:\docker-link\rabbitmq\data\rabbitmq1:/var/lib/rabbitmq/mnesia rabbitmq:3-management-alpine
```
