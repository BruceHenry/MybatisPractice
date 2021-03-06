# MybatisPractice
This project is a practice of Mybatis. Examples cover common usage of Mybatis framework. 
***

### Index
1. [Hello World](#hello-world)
2. [Basic CRUD](#basic-CRUD)
3. [One To Many](#one_to_many)
4. [Many To One](#many_to_one)
5. [Many To Many](#many_to_many)
6. [Dynamic SQL](#dynamic-SQL)
7. [Annotation](#annotation)
8. [log4j](#log4j)
9. [Transaction Support](#transaction-support)
10. [Lazy Loading](#lazy-loading)
11. [Page Split](#page-split)
12. [Cache](#cache)
13. [c3p0](#c3p0)
14. [Mybatis Generator](#mybatis-generator)
***

#### Preparation
Install MySQL, and set username=root and password=admin. <br>
You can install whatever database you want and set any username, but do not forget to change the code in the Configuration XML files.

__Required jar:__
- [Mybatis](./lib/mybatis-3.4.5.jar)
- [JDBC (MySQL)](./lib/mysql-connector-java-5.1.42-bin.jar)
- [log4j-api-2.9.1.jar](./lib/log4j-api-2.9.1.jar) 
- [log4j-core-2.9.1.jar](./lib/log4j-core-2.9.1.jar)
- [c3p0-0.9.5.2.jar](./lib/c3p0-0.9.5.2.jar) 
- [mchange-commons-java-0.2.11.jar](./lib/mchange-commons-java-0.2.11.jar)
- [mybatis-generator-core-1.3.5.jar](./lib/mybatis-generator-core-1.3.5.jar)
***
<a name="hello-world"/>

### 01. Hello World 
[Example Package Directory](./src/hello_world)
- [SQL](./src/hello_world/.sql)
- [POJO Java Class](./src/hello_world/Category.java)
- [POJO XML](./src/hello_world/Category.xml)
- [Configuration XML](./src/hello_world/helloWorld-config.xml)
- [main method](./src/hello_world/Main.java)

__Result:__ Print all the names of Category.
***
<a name="basic-CRUD"/>

### 02. Basic CRUD  
[Example Package Directory](./src/basic_CRUD) [(Annotation Way Here)](./src/annotation/CRUD)
- [POJO Java Class](./src/basic_CRUD/Category.java)
- [POJO XML](./src/basic_CRUD/Category.xml)
- [Configuration XML](./src/basic_CRUD/basicCRUD-config.xml)

__Methods:__
- [Insert](./src/basic_CRUD/Insert.java)
```
<insert id="addCategory" parameterType="Category">
    insert into category_ ( name ) values (#{name})
</insert>
```
- [Delete](./src/basic_CRUD/Delete.java)
```
<delete id="deleteCategory" parameterType="Category">
    delete from category_ where id= #{id}
</delete>
```
- [Update](./src/basic_CRUD/Update.java)
```
<update id="updateCategory" parameterType="Category">
    update category_ set name=#{name} where id=#{id}
</update>
```
- [Select One](./src/basic_CRUD/GetOne.java)
```
<select id="getCategory" parameterType="_int" resultType="Category">
    select * from   category_  where id= #{id}
</select>
```
- [Select All](./src/basic_CRUD/GetAll.java)
```
<select id="listCategory" resultType="Category">
    select * from   category_
</select>
```
- [Select Like](./src/basic_CRUD/GetLike.java) 

There is a better way using [`<bind>`](#bind).
```
<select id="listCategoryByName" parameterType="string" resultType="Category">
    select * from   category_  where name like concat('%',#{0},'%')
</select>
```
- [Select Multiple Condition](./src/basic_CRUD/GetMultipleCondition.java)
```
<select id="listCategoryByIdAndName" resultType="Category">
    select * from   category_  where id> #{id}  and name like concat('%',#{name},'%')
</select>
```
***
<a name="one_to_many"/>

### 03. One To Many 
__One__ Category object has __many__ Product objects.

[Example Package Directory](./src/one_to_many) [(Annotation Way Here)](./src/annotation/one_to_many)
- [SQL](./src/one_to_many/.sql)
- POJO Java Class:
    1. [Category](./src/one_to_many/Category.java)
    2. [Product](./src/one_to_many/Product.java)
- [Category XML](./src/one_to_many/Category.xml)
- [Configuration XML](./src/one_to_many/oneToMany-config.xml)
- [main method](./src/one_to_many/Main.java) Print all Category objects with related Product.

__Result:__ 
```
Category [id=1, name=UpdatedName]
	Product [id=1, name=product a, price=88.88]
	Product [id=2, name=product b, price=88.88]
	Product [id=3, name=product c, price=88.88]
Category [id=2, name=category2]
	Product [id=4, name=product x, price=88.88]
	Product [id=5, name=product y, price=88.88]
	Product [id=6, name=product z, price=88.88]
```
***
<a name="many_to_one"/>

### 04. Many To One 
__Many__  Product objects belongs to __one__ Category.

[Example Package Directory](./src/many_to_one) [(Annotation Way Here)](./src/annotation/many_to_one)
- [SQL](./src/many_to_one/.sql)
- POJO Java Class:
    1. [Category](./src/many_to_one/Category.java)
    2. [Product](./src/many_to_one/Product.java)
- POJO XML
    1. [Category](./src/many_to_one/Category.xml)
    2. [Product](./src/many_to_one/Product.xml)
- [Configuration XML](./src/many_to_one/manyToOne-config.xml)
- [main method](./src/many_to_one/Main.java) Print all Product objects with related Category.

__Result:__ 
```
Product [id=1, name=product a, price=88.88]	==>	Category [id=1, name=UpdatedName]
Product [id=2, name=product b, price=88.88]	==>	Category [id=1, name=UpdatedName]
Product [id=3, name=product c, price=88.88]	==>	Category [id=1, name=UpdatedName]
Product [id=4, name=product x, price=88.88]	==>	Category [id=2, name=category2]
Product [id=5, name=product y, price=88.88]	==>	Category [id=2, name=category2]
Product [id=6, name=product z, price=88.88]	==>	Category [id=2, name=category2]
```
***
<a name="many_to_many"/>

### 05. Many To Many 
__One__ Order has __many__ Product objects.<br/>
__One__ Product is in __many__ Order objects.

[Example Package Directory](./src/many_to_many) [(Annotation Way Here)](./src/annotation/many_to_many)
- [SQL](./src/many_to_many/.sql)
- POJO Java Class:
    1. [Category](./src/many_to_many/Category.java)
    2. [Product](./src/many_to_many/Product.java)
    3. [OrderItem](./src/many_to_many/OrderItem.java)
- POJO XML
    1. [Category](./src/many_to_many/Category.xml)
    2. [Product](./src/many_to_many/Product.xml)
    3. [OrderItem](./src/many_to_many/OrderItem.xml)
- [Configuration XML](./src/many_to_many/manyToMany-config.xml)
- [main method](./src/many_to_many/Main.java) First add a relation between Order and Product, then delete it.

__Result:__ 
```
Order001
	product a	88.879997	100
	product b	88.879997	100
	product c	88.879997	100
Order002
	product b	88.879997	100
	product c	88.879997	100
	product x	88.879997	100

*******Add Order 'Order001' <=> Product 'z'********
Order001
	product a	88.879997	100
	product b	88.879997	100
	product c	88.879997	100
	product z	88.879997	200
Order002
	product b	88.879997	100
	product c	88.879997	100
	product x	88.879997	100

*******Delete Order 'Order001' <=> Product 'z'********
Order001
	product a	88.879997	100
	product b	88.879997	100
	product c	88.879997	100
Order002
	product b	88.879997	100
	product c	88.879997	100
	product x	88.879997	100
```
***
<a name="dynamic-SQL"/>

### 06. Dynamic SQL 
[(Annotation Way Here)](./src/annotation/dynamic_SQL)
##### (1) if
Use one SQL for statements with a optional parameter.
```
<select id="listProduct" resultType="Product">
    select * from product_
    <if test="name!=null">
        where name like concat('%',#{name},'%')
    </if>
</select>
```

[Example Package Directory](./src/dynamic_SQL/if_tag)
- [Product Class](./src/dynamic_SQL/if_tag/Product.java)
- [Product XML](./src/dynamic_SQL/if_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/if_tag/if-config.xml)
- [main method](./src/dynamic_SQL/if_tag/Main.java)

__Result:__
```
Query All
Product [id=1, name=product a, price=88.88]
Product [id=2, name=product b, price=88.88]
Product [id=3, name=product c, price=88.88]
Product [id=4, name=product x, price=88.88]
Product [id=5, name=product y, price=88.88]
Product [id=6, name=product z, price=88.88]

Query Vaguely
Product [id=1, name=product a, price=88.88]
```
##### (2) where
Support for multiple optional parameters.
```
<select id="listProduct" resultType="Product">
    select * from product_ p
    <where>
        <if test="name!=null">
            and name like concat('%',#{name},'%')
        </if>
        <if test="price!=null and price!=0">
            and price > #{price}
        </if>
    </where>
</select>
```
[Example Package Directory](./src/dynamic_SQL/where_tag)
- [Product Class](./src/dynamic_SQL/where_tag/Product.java)
- [Product XML](./src/dynamic_SQL/where_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/where_tag/where-config.xml)
- [main method](./src/dynamic_SQL/where_tag/Main.java)

__Result:__
```
[Product [id=1, name=product a, price=88.88]]
Product [id=1, name=product a, price=88.88]
```

##### (3) set
Optional parameters for UPDATE statement.
```
<update id="updateProduct" parameterType="Product" >
    update product_
    <set>
        <if test="name != null">name=#{name},</if>
        <if test="price != null">price=#{price}</if>
    </set>
    where id=#{id}
</update>
```
[Example Package Directory](./src/dynamic_SQL/set_tag)
- [Product Class](./src/dynamic_SQL/set_tag/Product.java)
- [Product XML](./src/dynamic_SQL/set_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/set_tag/set-config.xml)
- [main method](./src/dynamic_SQL/set_tag/Main.java)

__Result:__
```
*****Original Data*****
Product [id=6, name=product z, price=99.99]

*****After Update*****
Product [id=6, name=product zz, price=99.99]
```

##### (4) trim
Customize AND/OR for WHERE and UPDATE
```
<select id="listProduct" resultType="Product">
        select * from product_
        <trim prefix="WHERE" prefixOverrides="AND |OR ">
            <if test="name!=null">
                and name like concat('%',#{name},'%')
            </if>
            <if test="price!=null and price!=0">
                and price > #{price}
            </if>
        </trim>
    </select>

    <update id="updateProduct" parameterType="Product" >
        update product_
        <trim prefix="SET" suffixOverrides=",">
            <if test="name != null">name=#{name},</if>
            <if test="price != null">price=#{price}</if>

        </trim>

        where id=#{id}
    </update>
```
[Example Package Directory](./src/dynamic_SQL/trim_tag)
- [Product Class](./src/dynamic_SQL/trim_tag/Product.java)
- [Product XML](./src/dynamic_SQL/trim_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/trim_tag/trim-config.xml)
- [main method](./src/dynamic_SQL/trim_tag/Main.java)

##### (5) choose
`choose...when...otherwise` is the `switch...case...default` in Mybatis.
```
<select id="listProduct" resultType="Product">
    SELECT * FROM product_
    <where>
        <choose>
            <when test="name != null">
                and name like concat('%',#{name},'%')
            </when>
            <when test="price !=null and price != 0">
                and price > #{price}
            </when>
            <otherwise>
                and id >1
            </otherwise>
        </choose>
    </where>
</select>
```
[Example Package Directory](./src/dynamic_SQL/choose_tag)
- [Product Class](./src/dynamic_SQL/choose_tag/Product.java)
- [Product XML](./src/dynamic_SQL/choose_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/choose_tag/choose-config.xml)
- [main method](./src/dynamic_SQL/choose_tag/Main.java)

##### (6) foreach
`foreach` is for `in` in SQL like `select * from product_ where id in(1,3,5)`
```
<select id="listProduct" resultType="Product">
    SELECT * FROM product_
    <where>
        <choose>
            <when test="name != null">
                and name like concat('%',#{name},'%')
            </when>
            <when test="price !=null and price != 0">
                and price > #{price}
            </when>
            <otherwise>
                and id >1
            </otherwise>
        </choose>
    </where>
</select>
```
[Example Package Directory](./src/dynamic_SQL/foreach_tag)
- [Product Class](./src/dynamic_SQL/foreach_tag/Product.java)
- [Product XML](./src/dynamic_SQL/foreach_tag/Product.xml)
- [Configuration XML](./src/dynamic_SQL/foreach_tag/foreach-config.xml)
- [main method](./src/dynamic_SQL/foreach_tag/Main.java)

__Result:__
```
Product [id=1, name=product a, price=88.88]
Product [id=3, name=product c, price=88.88]
Product [id=5, name=product y, price=88.88]
```

<a name='bind'/>

##### (7) bind 
An easy way to concat.
```
<!--Old concat way-->
<!--         <select id="listProduct" resultType="Product"> -->
<!--             select * from   product_  where name like concat('%',#{0},'%') -->
<!--         </select> -->

<select id="listProduct" resultType="Product">
    <bind name="likename" value="'%' + name + '%'" />
    select * from   product_  where name like #{likename}
</select>
```
[Example Package Directory](./src/dynamic_SQL/bind_tag)
***
<a name="annotation"/>

### 07. Annotation 
[Example Package Directory](./src/annotation)

- [Basic CRUD](./src/annotation/CRUD)
- [one_to_many](./src/annotation/one_to_many)
- [many_to_one](./src/annotation/many_to_one)
- [many_to_many](./src/annotation/many_to_many)
- [dynamic_SQL](./src/annotation/dynamic_SQL)

***
<a name="log4j"/>

### 08. log4j 
[Example Package Directory](./src/log)

__Required JAR:__ [log4j-api-2.9.1.jar](./lib/log4j-api-2.9.1.jar) and [log4j-core-2.9.1.jar](./lib/log4j-core-2.9.1.jar)
***
<a name="transaction-support"/>

### 09. Transaction Support 
Only INNODB support for Transaction.
`alter table category_ ENGINE  = innodb;`

***
<a name="lazy-loading"/>

### 10. Lazy Loading 
Do not do sub-query unless it is needed.
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>  
         <setting name="lazyLoadingEnabled" value="true" />  
         <setting name="aggressiveLazyLoading" value="false"/>  
    </settings>  
    
 ......

```

***
<a name="page-split"/>

### 11. Page Split 
__Way 1: PageHelper__

Mybatis-config.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>  
            <setting name="lazyLoadingEnabled" value="true" />  
            <setting name="aggressiveLazyLoading" value="false"/>  
        </settings>    
    <typeAliases>
      <package name=" "/>
    </typeAliases>
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
             
        </plugin>
    </plugins>
    ......
```

main method: `PageHelper.offsetPage(0, 5);` means showing 5 records from 0

__Way 2: `limit` in SQL__

POJO XML File
```
        <select id="listCategory" resultType="Category">
            select * from   category_ 
                <if test="start!=null and count!=null">
                    limit #{start},#{count}
                </if>
        </select>   
```

***
<a name="cache"/>

### 12. Cache 
##### Step 1: Add `<setting name="cacheEnabled" value="true"/>` in mybatis-config.xml
##### Step 2: Add `<cache/>` in POJO XML File
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
 
    <mapper namespace="com.how2java.pojo">
        <cache/>
        ......
```
##### Step 3: POJO Class `implements Serializable`

***
<a name="c3p0"/>

### 13. c3p0 
##### Step 1: Add [c3p0-0.9.5.2.jar](./lib/c3p0-0.9.5.2.jar) and [mchange-commons-java-0.2.11.jar](./lib/mchange-commons-java-0.2.11.jar)
##### Step 2: Create [C3P0DataSourceFactory Class](./src/c3p0/C3P0DataSourceFactory.java)
##### Step 3: Edit [mybatis-config.xml](./src/c3p0/mybatis-config.xml)

***
<a name="mybatis-generator"/>

### 14. Mybatis Generator 
Generate POJO and XML from tables in the database.
##### Step 1: Add [mybatis-generator-core-1.3.5.jar](./lib/mybatis-generator-core-1.3.5.jar)
##### Step 2: Create [generator-config.xml](./src/mybatis_generator/generator-config.xml)
##### Step 3: Create [Mybatis Generator main method](./src/mybatis_generator/GeneratorMain.java) and run. The pojo class, mapper class and pojo xml files will be generated.