
sharding jdbc,ehcache,mybatis,ThreadPoolTaskExecutor,netty,grpc(protobuf)   

1.netty
利用netty长连接远程netty服务,获取数据  
2.sharding jdbc  
https://shardingsphere.apache.org/  
大数据量操作下,按时间分表,此框架对某些复杂的sql不支持,可以自定义注释(@TargetDataSource(dataSourceKey = DataSourceKey.DB_slave)),切换数据源
在mysql中创建存储过程，任务时间，实现每天创建新表  
3.ehcache  
缓存加载的大量用户数据  
4.ThreadPoolTaskExecutor  
开启线程池,利用多线程处理io数据(Runtime.getRuntime().exec(cmd) 在linux执行命令),合理分配线程池大小  
5.gprc client/server(com.lbc.mo.service)  

[design_patterns](https://sourcemaking.com/design_patterns)

