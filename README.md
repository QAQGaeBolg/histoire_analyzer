# histoire_analyzer

## Grouping

## Intro

### Topic

十年来 Java 各大应用框架流行度分析

涉及到的框架：

web applications framework

- **Spring**: Enterprise-level Java application framework
- **Struts**: another MVC framework for enterprise-level Java applications


- **Grails**: Groovy-based web application framework
- **Play**: Reactive web & mobile framework for highly scalable Java applications
- **GWT(Google Web Toolkit)**: client-side Java apps deployed as JavaScript
- **Blade**:  Simple application framework with a minimal footprint
- **Vaadin**: Web application framework with a focus on UX, accessibility, and mobile
- **JHipster**: Web apps and microservices with Spring Boot and Angular/React
- **Vert.x**: Polyglot event-driven application framework for the Java Virtual Machine
- **Tapestry**: Component-oriented framework for highly scalable apps
- **Apache Spark**: Micro framework for web apps and REST APIs

- **Wicket**: Component-based web application framework for purists

Microservices ?  web ?

Ref: https://raygun.com/blog/popular-java-frameworks/

Database

- **Hibernate**: Object-relational mapping framework for a better database communication
- **MyBatis**: Persistence framework for easier SQL management

web services

- **Dropwizard**: a high-performance but straightforward Java framework for rapid development of RESTful web services.

building user interfaces

- **JSF (Java Server Faces)**: Component-based UI framework

- **PrimeFaces**: UI framework for Jakarta EE and Jakarta Server Faces

### 总体分析

- 总流行度：柱状图
- 十年流行度变化：动态增长图 / 折线图
  - 动态图须传数据：`[["popularity", "framework", "date"],[data],[],[]...]`
  - 折线图须传数据：`name:{framework1, framework2, ...,}, x:{2012, 2013, 2014, ...}, y:{data1, data2, ...} * frameworks`
- 十年增长率：百分比折线图 / 柱状图

Feature：可以选择具体框架图标进入图表中

总流行度 = stars数 + commits数 + 问题数量 + 回帖数量 （数量级及权重？）

每年流行度 = 仓库数 + commit数 + 问题数量 + 回帖数量


### 具体分析

点击对应框架的github图标，可以查看github数据信息，并根据时间或stars对仓库进行排序。

流程

爬数据进入数据库，每次打开都从数据库里取以呈现画面。点击按钮可以继续爬？

## 数据库设计

框架库：frameworks

- Primary

- Primary Key: framework （框架名）
- Attributes: 

## scrapper

github search 访问限制

https://docs.github.com/en/rest/search#about-the-search-api