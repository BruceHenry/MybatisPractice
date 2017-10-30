package many_to_one;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "many_to_one/manyToOne-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p+"\t==>\t"+ p.getCategory());
        }

        session.commit();
        session.close();
    }
}
