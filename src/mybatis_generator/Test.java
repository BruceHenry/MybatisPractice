package mybatis_generator;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis_generator.mapper.CategoryMapper;
import mybatis_generator.pojo.Category;
import mybatis_generator.pojo.CategoryExample;

public class Test {
    public static void main(String args[]) throws Exception {
        String resource = "mybatis_generator/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        CategoryExample example = new CategoryExample();
        example.createCriteria().andNameLike("%o%");
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        List<Category> cs = mapper.selectByExample(example);

        for (Category c : cs) {
            System.out.println(c.getName());
        }

    }
}