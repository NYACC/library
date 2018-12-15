package cn.albumen.library.dao;

import cn.albumen.library.bean.BookCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Repository
public interface BookCategoryDao {
    /**
     * 添加书籍分类
     *
     * @param bookCategory
     * @return
     */
    int add(BookCategory bookCategory);

    /**
     * 删除书籍分类
     *
     * @param bookCategory
     * @return
     */
    int delete(BookCategory bookCategory);

    /**
     * 修改书籍分类
     *
     * @param bookCategory
     * @return
     */
    int update(BookCategory bookCategory);

    /**
     * 通过ID查找分类
     *
     * @param bookCategory
     * @return
     */
    BookCategory selectById(BookCategory bookCategory);

    /**
     * 获取所有分类
     *
     * @return
     */
    List<BookCategory> selectAll();

    /**
     * 获取分类
     *
     * @param bookCategory 条数
     * @return
     */
    List<BookCategory> selectLimit(BookCategory bookCategory);
}
