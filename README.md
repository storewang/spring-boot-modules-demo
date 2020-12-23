# spring-boot-modules-demo

## spring-boot-modules-demo
此事例做了spring分模块化异步启动。在controller模块最后调用业务模块进行查询的简单例子。
```mermaid
graph TD;
  bus-->user;
  bus-->sys;
  user-->DB;
  sys-->DB;
```
