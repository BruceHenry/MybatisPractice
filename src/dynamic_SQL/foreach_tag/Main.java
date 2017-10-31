package dynamic_SQL.foreach_tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "dynamic_SQL/foreach_tag/foreach-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);

        List<Product> ps = session.selectList("listProduct", ids);
        for (Product p : ps) {
            System.out.println(p);
        }

        session.commit();
        session.close();

    }
}
