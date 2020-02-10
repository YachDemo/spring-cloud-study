### eureka-server 服务注册中心
##### 说明
1. eureka是一个高可用的组件，它没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成），
在默认情况下eureka server也是一个eureka client，必须指定一个server。
1. eureka是有界面的，启动项目，访问http://localhost:8761

