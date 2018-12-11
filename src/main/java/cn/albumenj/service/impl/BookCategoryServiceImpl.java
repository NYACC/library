package cn.albumenj.service.impl;

import cn.albumenj.bean.BookCategory;
import cn.albumenj.dao.BookCategoryDao;
import cn.albumenj.service.BookCategoryService;
import cn.albumenj.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        boolean flag = userSecurityService.checkIdCategoryStaff(bookCategory.getLoginedUserId());
        if(flag) {
            int row = bookCategoryDao.add(bookCategory);
            return (row == 1);
        }
        else {
            return false;
        }
    }

    /**
     * 删除分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public boolean delete(BookCategory bookCategory) {
        boolean flag = userSecurityService.checkIdCategoryStaff(bookCategory.getLoginedUserId());
        if(flag) {
            int row = bookCategoryDao.delete(bookCategory);
            return (row == 1);
        }
        else {
            return false;
        }
    }

    /**
     * 修改分类
     *
     * @param bookCategory
     * @return
     */
    @Override
    public boolean udpate(BookCategory bookCategory) {
        boolean flag = userSecurityService.checkIdCategoryStaff(bookCategory.getLoginedUserId());
        if(flag) {
            int row = bookCategoryDao.update(bookCategory);
            return (row == 1);
        }
        else {
            return false;
        }
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
        if(list.isEmpty()) {
            return null;
        }
        else {
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
        if(list.isEmpty()) {
            return null;
        }
        else {
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
