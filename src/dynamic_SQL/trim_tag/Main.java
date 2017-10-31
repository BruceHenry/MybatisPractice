package dynamic_SQL.trim_tag;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "dynamic_SQL/trim_tag/trim-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        System.out.println("*****Original Data*****");
        listAll(session);



        Product p = new Product();
        p.setId(6);
        p.setName("product zzz");
        p.setPrice(99.99f);
        session.update("updateProduct", p);

        System.out.println("\n*****After Update*****");
        listAll(session);

        session.commit();
        session.close();

    }

    private static void listAll(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("name","z");
        List<Product> ps2 = session.selectList("listProduct", params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }
}