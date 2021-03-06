package cn.albumen.library.service.impl;

import cn.albumen.library.bean.BookCategory;
import cn.albumen.library.dao.BookCategoryDao;
import cn.albumen.library.service.BookCategoryService;
import cn.albumen.library.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Albumen
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    BookCategoryDao bookCategoryDao;
    @Autowired
    UserSecurityService userSecurityService;

    /**
     * 添加分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public boolean add(BookCategory bookCategory) {
        int row = bookCategoryDao.add(bookCategory);
        return (row == 1);
    }

    /**
     * 删除分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public boolean delete(BookCategory bookCategory) {
        int row = bookCategoryDao.delete(bookCategory);
        return (row == 1);
    }

    /**
     * 修改分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public boolean update(BookCategory bookCategory) {
        int row = bookCategoryDao.update(bookCategory);
        return (row == 1);
    }

    /**
     * 获取所有分类
     *
     * @param bookCategory 用于鉴权
     * @return
     */
    @Override
    public List<BookCategory> selectAll(BookCategory bookCategory) {
        List<BookCategory> list = bookCategoryDao.selectAll();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    /**
     * 获取分类
     *
     * @param bookCategory 用于鉴权
     * @return
     */
    @Override
    public List<BookCategory> selectLimit(BookCategory bookCategory) {
        List<BookCategory> list = bookCategoryDao.selectLimit(bookCategory);
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    /**
     * 通过ID获取分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public BookCategory selectById(BookCategory bookCategory) {
        return bookCategoryDao.selectById(bookCategory);
    }
}
