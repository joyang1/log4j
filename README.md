## log4j.properties
Java的爱好者们，一定对log4j是特别的熟悉，基本大多数项目都会选择log4j、slg4j来进行log记录。<br/>
但是对于log4j.properties的配置可能理解的不是特别熟悉，所有我准备整理一下，同时也提高一下<br/>
自己对于log4j.properties的理解。

## log4j配置文件的说明

#### rootLogger说明

rootLogger 指定log打印源，DEBUG为默认打印级别<br/>
在rootLogger里面指定的打印源，在使用log4j打印log的时候，就会往所有rootLogger指定的打印源里面打印log信息。

- console 打印源为控制台 对应log4j.appender.console
- file 打印源为文件 对应log4j.appender.file
- kafka 打印源为kafka 对应log4j.appender.kafka

log4j.logger.org.apache.kafka 打印源为kafka时指定log默认打印级别，不设置不会打印log到kafka

``` properties
log4j.rootLogger=DEBUG,console,file,kafka
log4j.logger.org.apache.kafka=WARN
```
#### 打印源为控制台

``` properties
#输出日志到控制台
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=all
log4j.appender.console.layout=org.apache.log4j.PatternLayout
#控制台日志格式
log4j.appender.console.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c\:%L]-[%p] %m%n
```

#### 打印源为kafka

``` properties
#输出日志到kafka
log4j.appender.kafka=org.apache.kafka.log4jappender.KafkaLog4jAppender
#设置topic
log4j.appender.kafka.topic=pro-log
#设置kafka连接地址
log4j.appender.kafka.brokerList=localhost:9092
log4j.appender.kafka.compressionType=none
log4j.appender.kafka.requiredNumAcks=0
#设置是否异步
log4j.appender.kafka.syncSend=true
log4j.appender.kafka.level=DEBUG
log4j.appender.kafka.layout=org.apache.log4j.PatternLayout
log4j.appender.kafka.layout.ConversionPattern= %m
```

#### 打印源为file

``` properties
# appender file
log4j.appender.file=org.apache.log4j.RollingFileAppender
#设置log文件名
log4j.appender.file.file=logs/slf4j4json.log
#log是否追加
log4j.appender.file.append=true
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p %-60c %x - %m%n
#文件大小
log4j.appender.file.MaxFileSize=100MB
#文件保留个数
log4j.appender.file.MaxBackupIndex=5
```

#### 单独指定打印源

比如我只想往kafka中写入log、或者我只想往文件中写入log，我们可以按下面的方式进行配置打印源。<br/>
通过log4j.logger配置单个的打印源，不实用rootLogger配置。

``` properties

# 单独指定打印源为kafka
log4j.logger.kafka_log=INFO,kafka_log
log4j.logger.org.apache.kafka=INFO
log4j.appender.kafka_log=org.apache.kafka.log4jappender.KafkaLog4jAppender
log4j.appender.kafka_log.topic=admin-app-log
log4j.appender.kafka_log.brokerList=localhost:9092
log4j.appender.kafka_log.compressionType=none
log4j.appender.kafka_log.requiredNumAcks=0
log4j.appender.kafka_log.syncSend=false
log4j.appender.kafka_log.layout=org.apache.log4j.PatternLayout
log4j.appender.kafka_log.layout.ConversionPattern= %m

# 单独指定打印源为file
log4j.logger.file_log=INFO,file_log
log4j.appender.file_log=org.apache.log4j.RollingFileAppender
log4j.appender.file_log.file=logs/slf4j4json.log
log4j.appender.file_log.append=true
log4j.appender.file_log.layout=org.apache.log4j.PatternLayout
log4j.appender.file_log.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p %-60c %x - %m%n
log4j.appender.file_log.MaxFileSize=100MB
log4j.appender.file_log.MaxBackupIndex=5

```

如上配置好之后，使用LoggerFactory.getName("kafka_log")获取kafka打印源； <br/>
LoggerFactory.getName("file_log")获取file打印源

## 具体实现demo
[demo地址](https://github.com/joyang1/log4j)

## 拓展
[json格式log打印](https://github.com/joyang1/slf4j4json) <br/>
[json格式log打印详细介绍](https://blog.tommyyang.cn/2018/08/29/JsonLoggerForJava-2018/)

## 讨论
有问题欢迎大家指正，在我的博客进行评论、讨论。<br/>
地址：[log4j配置文件说明](https://blog.tommyyang.cn/2018/12/09/log4j%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E6%95%B4%E7%90%86-2018/)