### Spring Cloud 学习Demo
参考[方志朋](https://github.com/forezp/SpringCloudLearning "方志朋")的git
#### 时间线
##### 2020年2月10日
项目搭建，Eureka服务注册发现
##### 2020年2月11日 
网关换成gateway</p>
**相关概念:**
- Route(路由):这是网关的的基本构建块。由一个id，一个目标id，一个目标url，一组断言和一组过滤器定义。如果断言为真则路由匹配。
- Predicate（断言）：这是一个java8的Predicate。输入类型是一个ServerWebExchange。我们可以使用它来匹配来着HTTP的请求的任何内容，例如headers参数。
- Filter（过滤器）：这是这是`org.springframework.cloud.gateway.filter.GatewayFilter`的实例，我们可以使用它修改请求和响应。

**流程：**

客户端向Spring Cloud GateWay发出请求。如果GateWay Handler Mapping中找到与请求相匹配的路由，将其发送到GateWay Web Handler。Handler再通过指定的过滤器
来将请求发送到我们实际的服务执行业务逻辑，然后返回。

**特征：**

- 基于Spring Framework 5，Project Reactor和Spring Boot2.0
- 动态路由
- 集成Hystrix断路器
- 集成Spring Cloud DiscoveryClient
- 易于编写的Predicate和Filters
- 限流
- 路径重写

- [x] 选中
- [ ] 未选中 