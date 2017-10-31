package annotation.one_to_many;

import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface ProductMapper {

    @Select(" select * from product_ where cid = #{cid}")
    public List<Product> listByCategory(int cid);

}