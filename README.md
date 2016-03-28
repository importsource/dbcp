# dbcp
一个对jdbc进行简单封装的微服务数据库访问框架

###只需要两行代码便可实现

```java
Connection conn = DbcpConnection.getConnection();
String name = (String) DBClient.single(conn, "select name from user where name=?", "贺卓凡");
```
