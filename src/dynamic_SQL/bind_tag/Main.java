package dynamic_SQL.bind_tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "dynamic_SQL/bind_tag/bind-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Map<String, String> params = new HashMap();
        params.put("name", "z");

        List<Product> ps = session.selectList("listProduct", params);
        for (Product p : ps) {
            System.out.println(p);
        }

        session.commit();
        session.close();

    }

}