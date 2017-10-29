package categoryCRUD;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pojo.Category;

public class GetMultipleCondition {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Map<String,Object> params = new HashMap<>();
        params.put("id", 3);
        params.put("name", "cat");

        List<Category> cs = session.selectList("listCategoryByIdAndName",params);
        for (Category c : cs) {
            System.out.println(c.getName());
        }

        session.commit();
        session.close();

    }
}
