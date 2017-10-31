package annotation.many_to_one;


import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface ProductMapper {
    @Select(" select * from product_ ")
    @Results({
            @Result(property = "category", column = "cid", one = @One(select = "annotation.many_to_one.CategoryMapper.get"))
    })
    public List<Product> list();
}