# PBL2
 4/15 ~ 4/20

JAVA SDK 11

## You must set up application.properties

#spring.h2.console.enabled=true

#spring.datasource.url=jdbc:h2:mem:testdb

spring.datasource.url=jdbc:mysql://databasepbl.ctly38plxhxy.ap-northeast-2.rds.amazonaws.com:3306/mydatabasepbl

spring.datasource.username=root

spring.datasource.password=test1234

spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true

logging.level.org.hibernate.type.descriptor.sql=trace

spring.thymeleaf.cache=false

spring.thymeleaf.enabled=true

spring.thymeleaf.prefix=classpath:/templates/

spring.thymeleaf.suffix=.html

