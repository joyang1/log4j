## log4j.properties
Java的爱好者们，一定对log4j是特别的熟悉，基本大多数项目都会选择log4j、slg4j来进行log记录。<br/>
但是对于log4j.properties的配置可能理解的不是特别熟悉，所有我准备整理一下，同时也提高一下<br/>
自己对于log4j.properties的理解。

## log4j配置文件的说明
#### rootLogger说明
rootLogger 指定log打印源，DEBUG为默认打印级别<br/>
- console 打印源为控制台 对应log4j.appender.console
- file 打印源为文件 对应log4j.appender.file
- kafka 打印源为kafka 对应log4j.appender.kafka
log4j.logger.org.apache.kafka 打印源为kafka时指定log默认打印级别，不设置不会打印log到kafka
```
log4j.rootLogger=DEBUG,console,file,kafka
log4j.logger.org.apache.kafka=WARN
```
#### 打印源为控制台
```
#输出日志到控制台
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=all
log4j.appender.console.layout=org.apache.log4j.PatternLayout
#控制台日志格式
log4j.appender.console.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c\:%L]-[%p] %m%n
```

#### 打印源为kafka
```
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
```
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

## 具体实现demo
[demo地址](https://github.com/joyang1/log4j)

## 拓展
[json格式log打印](https://github.com/joyang1/slf4j4json)
[json格式log打印jar](http://blog.tommyyang.cn/2018/08/29/JsonLoggerForJava/)

## 讨论
有问题欢迎大家指正，在我的博客进行评论、讨论。<br/>
地址：[log4j配置文件说明](http://blog.tommyyang.cn/2018/12/09/log4j%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E6%95%B4%E7%90%86/)