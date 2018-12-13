package cn.albumen.library.service;

import cn.albumen.library.bean.BookCategory;

import java.util.List;

/**
 * @author Albumen
 */
public interface BookCategoryService {
    /**
     * 添加分类
     *
     * @param bookCategory
     * @return
     */
    boolean add(BookCategory bookCategory);

    /**
     * 删除分类
     *
     * @param bookCategory
     * @return
     */
    boolean delete(BookCategory bookCategory);

    /**
     * 修改分类
     *
     * @param bookCategory
     * @return
     */
    boolean update(BookCategory bookCategory);

    /**
     * 获取所有分类
     *
     * @param bookCategory 用于鉴权
     * @return
     */
    List<BookCategory> selectAll(BookCategory bookCategory);

    /**
     * 获取分类
     *
     * @param bookCategory 用于鉴权
     * @return
     */
    List<BookCategory> selectLimit(BookCategory bookCategory);

    /**
     * 通过ID获取分类
     *
     * @param bookCategory
     * @return
     */
    BookCategory selectById(BookCategory bookCategory);
}
