# Java Framwork Overdose

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

- 总流行度：柱状图、词云
  - 数据：`[[framework1,framework2,...], [num1, num2, num3...]]`, `[{frame1:num1}, {frame2:num2},...]`
- 十年活跃度变化：动态增长图 / 折线图
  - 动态图须传数据：`[["popularity", "framework", "date"],[data],[],[]...]`
  - 折线图须传数据：`name:{framework1, framework2, ...,}, x:{2012, 2013, 2014, ...}, y:{data1, data2, ...} * frameworks`

Feature：可以选择具体框架图标进入图表中

总流行度 = stars数 + forks数 + （问题数量 + 回帖数量）

每年活跃度 = 创建仓库数/commit数（月为基本单位） （问题数量 + 回帖数量）

数量级的差异：spring 和 sparks 框架与其他框架不在一个数量级上，另外两者同样相差较远。因而我们在爬取了两者的高stars数仓库后对整体的数据量进行了控制。由于其他框架的仓库数大概在1w条上下，Spring仓库的数目在60w左右，sparks在10w左右，我们对两者分别取了5w和2w条左右的数据。

### 整合数据库

将所有框架的仓库整合在一个数据库中，可以点击访问，并进行查询或通过stars、forks、创建时间排序。

爬数据进入数据库，每次打开都从数据库里取以呈现画面。点击按钮可以继续爬？

## 数据库设计

框架库：frameworks

- Primary

- Primary Key: framework （框架名）
- Attributes:

## scrapper

github search 访问限制

https://docs.github.com/en/rest/search#about-the-search-api

原本的打算是分别挖掘CSDN、知乎、GitHub和Stack Overflow等平台的关于Java Spring的相关数据。可是遇到了jsoup无法执行JavaScript，我们所访问的网页并不包含我们所需要的div数据，所以CSDN和知乎的数据无法爬取。

![](graph/graph2-1.png)

可以看到这里的document中不存在想要的#app的子元素。
另一方面，stack overflow有着captcha（全自动区分计算机和人类的图林测试），脚本依然无法访问。

![](graph/graph2-2.png)

所以，我们爬取了再GitHub上的关于Java Spring的相关数据。在GitHub主页搜索“Java Spring”词条，可以搜索到100页每页10份的相关结果。我们爬取这些搜索结果的被浏览量和最后更新日期，从而得到1000份相关数据。
由于有100页的搜索结果，爬取过程需要访问近似网页100次。为了保证爬虫的礼貌性，我们设置了每次访问后100毫秒的延迟时间。但是这在访问第十次网页的时候还是跳出了HTTP 429的请求过多错误。

![](graph/graph2-3.png)

发现短时间内只能连续访问九次，所以我们又让脚本在每访问九次之后做一个10000毫秒的延迟，也就是十秒。发现还是无法第十次访问网页。所以我们将延迟扩大至60秒，发现能够正常运行，直至第四十六次访问网页，再一次跳出了HTTP 429的错误。为了能够稳定获取数据，我们最后将延迟时间调整至120秒即120000毫秒之后，脚本方能正常运行，得到1000条目标数据。整个脚本运行时间大概在23分钟左右。

我们分别爬取了在GitHub上搜索词条“Java Spring”、“Spring boot”、“Spring MVC”、“Spring Cloud”、“Spring Data”、“Spring Security”和“Spring Batch”。每个词条分别获取1000条相关数据，来存入后端的数据库。

之后，我们尝试通过GET的请求方法，可以一次获取所有的网页信息。并通过设置per_page=100(最多为100)，让我们可以在一次访问中获取100条目标信息。相比之前的每次访问中获取10条数据，效率提高了很多。但是该方法在短时间内多次对网页进行访问，还是会有HTTP 403禁止访问的报错现象。

![](graph/graph2-4.png)

获取的所有数据通过Json格式传输至数据库。

## frontend

The frontend is build based on Vue framework. 

The frontend resource is packed onto an ISS server, thus the website can be browsed 
on the browser directly and there is no need of running the whole Vue project. 

The interactive mode, some picture resources and all of bgm resources refers to the game 
*Needy Girl Overdose*. 

The live2d character called Histoire is created artificially by Cubism, who is a character of 
the game *Hyperdimention Neptunia: Rebirth 3*.
The basic motion such as breathing and looking at the mouse is involved.
There is also a demo character model of official.

The implementation of all kinds of charts refers to the echart API.

## acknowedgement
Official documents including echarts, Cubism, Vue, Spring, MyBatis and other CSDN articles.

