package annotation.many_to_many;


import org.apache.ibatis.annotations.Select;


public interface ProductMapper {

    @Select("select * from product_ where id = #{id}")
    public Product get(int id);
}