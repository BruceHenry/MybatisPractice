package annotation.CRUD;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "annotation/CRUD/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        add(mapper);
        delete(mapper);
        get(mapper);
        update(mapper);
//        listAll(mapper);

        session.commit();
        session.close();

    }

    private static void update(CategoryMapper mapper) {
        Category c = mapper.get(1);
        c.setName("NewName");
        mapper.update(c);
        System.out.println("\n*****Updated List:*****");
        listAll(mapper);
    }

    private static void get(CategoryMapper mapper) {
        Category c = mapper.get(1);
        System.out.println("\nGet a object: "+c.getName());
    }

    private static void delete(CategoryMapper mapper) {
        mapper.delete(7);
        System.out.println("\n*****Delete a object, new list:*****");
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("NewCategory");
        mapper.add(c);
        System.out.println("\n*****Add a object, new list:*****");
        listAll(mapper);
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}