package annotation.many_to_one;


import org.apache.ibatis.annotations.Select;


public interface CategoryMapper {
    @Select(" select * from category_ where id = #{id}")
    public Category get(int id);

}