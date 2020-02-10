##### 此章坑汇总，根据教程评论得出
获取github上的配置信息应该是 http://localhost:8888/config-client/dev 而不是： http://localhost:8888/foo/dev 
既然你都说了路由规则了，为啥还写 foo/dev/ 呢。
看了半天不知道这个路由读取的什么配置 这一节踩了两个坑，给大家说下。
1：客户端的spring.application.name配置config-clent是和Git服务器上面的文件名相对应的，如果你的客户端是其他名字就报错找不到参数。
作者的Git上面是有这个config-client-dev的配置文件的所以是config-clent，作者在这里没有说明，大家注意。
2：客户端加载到的配置文件的配置项会覆盖本项目已有配置，比如客户端你自己配置的端口是8881，但是如果读取到config-clent-dev这个配置文件中也有配置端口为8882，那么此时客户端访问的地址应该是8882.这两个坑，请大家注意！！！
 为什么返回的数据中label为null不是master,http://localhost:8888/config-client/dev/master,地址后面加上/master即可