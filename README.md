# SpringCloud Alibaba with Seata 2023 Demo

### 技术栈

|          依赖          |       版本       |
|:--------------------:|:--------------:|
| Spring Cloud Alibaba | 2022.0.0.0-RC1 |
|     Spring Cloud     |    2022.0.0    |
|     Spring Boot      |     3.0.1      |
|     mybatis plus     |    3.5.3.1     |

|      中间件       |    版本    |
|:--------------:|:--------:|
|     Seata      |  1.6.1   | 
|     Nacos      |  2.2.0   |
|     mysql      |  8.0.15  |
|     docker     | 20.10.17 |
| docker-compose |  2.10.2  |

## 说明

* 没有关于User的操作,因为是围绕事务Demo,在这里并不浪费时间去添加
* 需要在Nacos添加seata的[配置项]()
### 创建订单执行顺序

1. 通过Mvc-config模拟一个用户登录的操作,让后续一系列操作都能获取用户的金钱之类的操作
2. 创建订单-> 扣减库存-> 扣减账户余额-> 消息队列物流操作(这里懒得弄)

