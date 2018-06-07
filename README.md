# mybatis dao层注解、mapper、domain生成工具
## 1.说明
```$xslt
mybatis-annotation-plugins是一款mybatis注解生成工具，主要用于产生mybatis的model、mapper、dao层文件
```

## 2. 引用pulgins文件
```$xslt
1.1 引入数据库驱动对用的包  以mysql为例 （已引入请忽略）
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.25</version>
</dependency>
        
1.2 引入mybatis依赖       （已引入请忽略）
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.3.1</version>
</dependency>

1.3 引入mybatis-annotation-plugins插件
<plugin>
    <groupId>edu.whu</groupId>
    <artifactId>mybatis-annotation-plugins</artifactId>
    <version>1.4</version>
</plugin>
```

## 3.添加配置文件
```$xslt
在resource文件目录下创建mybatis-annotation-config.yml,文件内容如下:
database:
 driver: com.mysql.jdbc.Driver                      #数据库驱动
 uri: jdbc:mysql://10.10.87.56:3306/focus_sale_dev  #数据库地址
 username: root                                     #用户名
 password: dev#pass                                 #用户密码

packages:
 model:
  name: whu.jack.h5player.model                     #生成model所在的包
  target: src/main/java  #生成model文件的目录
 dao:
  name: whu.jack.h5player.dao                       #生成dao层文件
  target: src/main/java                             #生成dao文件所在的目录
 mapper:
  name: mapper                                      #生成mapper层文件
  target: src/main/resources                        #生成mapper文件所在的目录

tableConfigurations:
 - name: example_table_1                            #table名称
   enableSelect: true                               #是否生成select sql,其他sql有待更新 可选填写（true|false）
   enableInsert: true   
   enableUpdate: true   
   enableDelete: true   
 - name: example_table_2 
   enableSelect: true
```

## 4.执行插件
找到maven插件并执行mybatis-annotation-plugins中generator

  