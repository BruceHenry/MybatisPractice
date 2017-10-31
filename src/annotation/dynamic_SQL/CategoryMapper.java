package annotation.dynamic_SQL;


import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;


public interface CategoryMapper {

    @InsertProvider(type = CategoryDynaSqlProvider.class, method = "add")
    public int add(Category category);

    @DeleteProvider(type = CategoryDynaSqlProvider.class, method = "delete")
    public void delete(int id);

    @SelectProvider(type = CategoryDynaSqlProvider.class, method = "get")
    public Category get(int id);

    @UpdateProvider(type = CategoryDynaSqlProvider.class, method = "update")
    public int update(Category category);

    @SelectProvider(type = CategoryDynaSqlProvider.class, method = "list")
    public List<Category> list();
}