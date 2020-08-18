package com.jk.mapper;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select bk.*,ty.name as typeName from  " +
            "t_book_xmt bk  " +
            "left join t_book_type_xmt ty on ty.id = bk.type_id")
    List<BookBean> selectUserList();

    @Select("select * from t_book_tree_xmt where pid=#{value}")
    List<TreeBean> findTreeChildren(Integer id);

    @Delete("delete from t_book_xmt where id = #{value}")
    void deleteBook(Integer ids);

    @Select("select * from t_book_type_xmt")
    List<TypeBean> findTreeList();

    @Insert("insert into t_book_xmt(name,price,details,type_id,timestamp) values(#{name},#{price},#{details},#{typeId},#{timestamp})")
    void saveBook(BookBean bookBean);

    @Update("update t_book_xmt set name=#{name},price=#{price},details=#{details},type_id=#{typeId},timestamp=#{timestamp} where id = #{id}")
    void updateBook(BookBean bookBean);

    @Select("select * from t_book_xmt where id = #{value}")
    BookBean findEditBookPage(Integer id);

    @Select("select * from t_book_type_xmt")
    List<TypeBean> findtypeBeans();
}
