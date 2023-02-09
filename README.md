# SpringCloud Alibaba with Seata 2023 Demo

### 技术栈

|          依赖          |       版本       |        说明         |
|:--------------------:|:--------------:|:-----------------:|
| Spring Cloud Alibaba | 2022.0.0.0-RC1 | Spring Cloud 加强配置 |
|     Spring Cloud     |    2022.0.0    |       微服务框架       |
|     Spring Boot      |     3.0.1      |      Web 框架       |
|      Open Feign      |      4.0       |     声明式服务调用组件     |
|     loadbalancer     |      4.0       |      负载均衡调度       |
|       sentinel       |     1.8.6      |      分布式流量控制      |
|     mybatis plus     |    3.5.3.1     |      ORM 框架       |

|      中间件       |    版本    |    说明     |
|:--------------:|:--------:|:---------:|
|     Seata      |  1.6.1   |  分布式事务框架  |
|     Nacos      |  2.2.0   | 服务注册发现和配置 |
|     mysql      |  8.0.15  |    数据库    |
|     docker     | 20.10.17 |    容器化    |
| docker-compose |  2.10.2  |   容器编排    |

## 说明

*

没有关于User的操作,因为是围绕事务Demo,在这里并不浪费时间去添加,只要继承了mvc-config就会默认用户id=1[用户默认id设置](./service/mvc-config/src/main/java/com/example/filter/LoginUserInfoInterceptor.java)

* Nacos初始化的时候,[初始化sql](./docker-starter-a0e35/mysql/init/nacos-init.sql)
  插入了seata的[配置数据](./docker-starter-a0e35/seata/seataServer.properties)

### 创建订单执行顺序

1. 通过Mvc-config模拟一个用户登录的操作,让后续一系列操作都能获取用户的金钱之类的操作
2. 创建订单-> 扣减库存-> 扣减账户余额-> 消息队列物流操作(这里懒得弄)

### 测试流程

1. 打开[docker-compose.yml](./docker-starter-a0e35/docker-compose.yml)跑起来,使用idea启动 或
   控制台执行`docker-compose up -d` 即可
2. 将三个Service服务启动 (account / goods / order)
3. 打开 [test.http](test.http) 发送请求测试
4. 修改数据库某商品库存为0 可测试事务回滚

