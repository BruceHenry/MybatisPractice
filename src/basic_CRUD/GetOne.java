package basic_CRUD;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class GetOne {
    public static void main(String[] args) throws IOException {
        String resource = "basic_CRUD/basicCRUD-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Category c = session.selectOne("getCategory", 1);

        System.out.println("\"session.selectOne()\" result: " + c.getName());


        session.commit();
        session.close();
    }
}
