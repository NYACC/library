package cn.albumen.library.dao;

import cn.albumen.library.bean.BookCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Mapper
@Repository
public interface BookCategoryDao {
    /**
     * 添加书籍分类
     *
     * @param bookCategory
     * @return
     */
    @Insert({"insert into book_category(name)" +
            "   values (#{name})"})
    int add(BookCategory bookCategory);

    /**
     * 删除书籍分类
     *
     * @param bookCategory
     * @return
     */
    @Delete({"delete from book_category" +
            "   where id = #{id}"})
    int delete(BookCategory bookCategory);

    /**
     * 修改书籍分类
     *
     * @param bookCategory
     * @return
     */
    @Update({"update book_category " +
            "   set name = #{name}" +
            "   where id = #{id}"})
    int update(BookCategory bookCategory);

    /**
     * 通过ID查找分类
     *
     * @param bookCategory
     * @return
     */
    @Select({"select * from book_category" +
            "   where id = #{id}"})
    BookCategory selectById(BookCategory bookCategory);

    /**
     * 获取所有分类
     *
     * @return
     */
    @Select({"select * from book_category"})
    List<BookCategory> selectAll();

    /**
     * 获取分类
     *
     * @param bookCategory 条数
     * @return
     */
    @Select({"<script>" +
            "   select * from book_category" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<BookCategory> selectLimit(BookCategory bookCategory);
}
