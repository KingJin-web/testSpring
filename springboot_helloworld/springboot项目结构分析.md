1. pom.xml文件分析:
```xml   
        <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>2.4.5</version>
           <relativePath/> <!-- lookup parent from repository -->
       </parent>
   ```
2. .gitignore   git的忽略文件.
   表示哪些目录，文件不上传到git服务器.
3. 启动类上的启动注解:   ******
    @SpringBootApplication
4. resources中
    a. 配置文件(两种) :
           application.properties
           application.yml
    b. static: 静态资源
       templates:模板页面(  themleaf, freemarker )