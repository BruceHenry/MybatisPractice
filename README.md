# MybatisPractice
This project is a practice of Mybatis

### 01. Hello World
[Package](./src/hello_world)

- [SQL](./src/hello_world/.sql)
- [POJO Java Class](./src/hello_world/Category.java)
- [POJO XML](./src/hello_world/Category.xml)
- [Configuration XML](./src/hello_world/helloWorld-config.xml)
- [main method](./src/hello_world/Main.java)

__Result:__ Print all the names of Category.

### 02. Basic CRUD
[Package](./src/basic_CRUD)

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

- [SQL](./src/one_to_many/.sql)
- POJO Java Class:
    1. [Category](./src/one_to_many/Category.java)
    2. [Product](./src/one_to_many/Product.java)
- [Category XML](./src/one_to_many/Category.xml)
- [Configuration XML](./src/one_to_many/oneToMany-config.xml)
- [main method](./src/one_to_many/Main.java)

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