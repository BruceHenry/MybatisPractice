package basic_CRUD;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class GetLike {

    public static void main(String[] args) throws IOException {
        String resource = "basic_CRUD/basicCRUD-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Category> cs = session.selectList("listCategoryByName","cat");
        for (Category c : cs) {
            System.out.println(c.getName());
        }

        session.commit();
        session.close();

    }
}
