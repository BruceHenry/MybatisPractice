# MybatisPractice
This project is a practice of Mybatis. Examples cover common usage of Mybatis framework. 
<hr/>

#### Preparation
Install MySQL, and set username=root and password=admin. 

*You can install whatever database you want and set any username, do not forget to change the code in the Configuration XML files.*

__Required jar:__[Mybatis](./lib/mybatis-3.4.5.jar) and [JDBC (MySQL)](./lib/mysql-connector-java-5.1.42-bin.jar)

### 01. Hello World
[Package Directory](./src/hello_world)
- [SQL](./src/hello_world/.sql)
- [POJO Java Class](./src/hello_world/Category.java)
- [POJO XML](./src/hello_world/Category.xml)
- [Configuration XML](./src/hello_world/helloWorld-config.xml)
- [main method](./src/hello_world/Main.java)

__Result:__ Print all the names of Category.

### 02. Basic CRUD
[Package Directory](./src/basic_CRUD)
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

### 03. One To Many
__One__ Category object has __many__ Product objects.

[Package Directory](./src/one_to_many)
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

### 04. Many To One
__Many__  Product objects belongs to __one__ Category.

[Package Directory](./src/many_to_one)
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

### 05. Many To Many
__One__ Order has __many__ Product objects.<br/>
__One__ Product is in __many__ Order objects.

[Package Directory](./src/many_to_many)
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