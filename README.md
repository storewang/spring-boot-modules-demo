# spring-boot-modules-demo

## spring-boot-modules-demo
此事例做了spring分模块化异步启动。在controller模块最后调用业务模块进行查询的简单例子。
```flow
 application --> businessService --> { user, sys } --> DB
 
 思路：在application启动时，
      1. 异步启动，user,sys,bus模块,  由于busservie依赖user和sys模块，所以把依赖的相关注入放入applicationContext的needsLasydependency的队列中
      2. application中的controller依赖busservice也放入applicationContext的needsLasydependency的队列中，由于是异步启动，application启动完成，其他模块也未必会启动完成。
         这里使用了InitializingBean.afterPropertiesSet注入点进行添加,也可以使用其他方法进行加入：ModuleApplicationContext.addLasyDependency(lazyDependency);
         
      3. 在子模块启动完成，会发送ModuleFinishedEvent事件,当所有子模块都启动完成，调用ModuleApplicationContext.resolveLazyDependencys()对needsLasydependency的队列中
         需要依赖注入的重新注入。

 流程: 1. ApplicationConf.init --> 异步启动子模块
      2. MouduleBeanfactoryProcess --> ModuleApplicationContext.addModuleApplication(moduleName,beanFactory); 子模块或是主模块启动时注册到全局context的模块变量中
      3. MouduleBeanPostProcess --> postProcessAfterInitialization 子模块bean初始化完成，注册到全局context的bean列表中.
      4. MouduleContextFinishedProcess --> onApplicationEvent 子模块启动完成发送ModuleFinishedEvent消息:parent.publishEvent(finishedEvent);
      5. ModuleFinishedListener --> onApplicationEvent 监听是否所有子模块启动完成，如果都启动完成，调用:ModuleApplicationContext.resolveLazyDependencys();
         进行需要再注入的bean属性进行再注入，从全局context的bean列表中获取属性值.
         
```
