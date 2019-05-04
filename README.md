# 权限管理

> 包含如下内容：
> 1、登陆、登出、心跳
> 2、用户管理
> 3、角色管理
> 4、权限管理


- swagger文档地址：
  [http://localhost:8080/security/v1/swagger-ui.html](http://localhost:8080/security/v1/swagger-ui.html "http://localhost:8080/security/v1/swagger-ui.html")

- markdown文档地址：
  本项目根目录doc路径

- 注意：
本项目在查询“角色对应的权限”，以及“用户对应的角色”时，存在查询效率的“n+1问题”。也就是说，假设有10个角色，那么
在查询这10个角色对应的权限时，会发出10+1个sql。

> 参考：
- 1、shiro的demo
https://github.com/CaiBaoHong/elegant-shiro-boot/
- 2、springboot+shiro官网教程
http://shiro.apache.org/spring-boot.html
- 3、mybatis中collection一对多关联查询分页出错
https://blog.csdn.net/baidu_38116275/article/details/78622669
- 4、mybatis中collection子查询不执行
https://blog.csdn.net/yu514950381/article/details/82491127
- 5、springboot+shiro的start方式使用教程
https://segmentfault.com/a/1190000014479154
- 6、基于redis的会话管理
https://blog.csdn.net/xieliaowa9231/article/details/78995465


