package cn.albumenj.service.impl;

import cn.albumenj.bean.BookDetail;
import cn.albumenj.constant.RentConst;
import cn.albumenj.dao.BookDetailDao;
import cn.albumenj.dto.BookSearchDto;
import cn.albumenj.service.BookDetailService;
import cn.albumenj.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Albumen
 */
@Service
public class BookDetailServiceImpl implements BookDetailService {
    @Autowired
    BookDetailDao bookDetailDao;
    @Autowired
    UserSecurityService userSecurityService;

    /**
     * 添加书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public boolean add(BookDetail bookDetail) {
        boolean permission = userSecurityService.checkIdCategoryStaff(bookDetail.getLoginedUserId());
        if(permission) {
            bookDetail.setRent(RentConst.UNRENT);
            boolean flag = true;
            if(bookDetail.getBookNum().equals(0)) {
                return false;
            }
            for(int i = 0;i<bookDetail.getBookNum();i++){
                boolean insert = (bookDetailDao.add(bookDetail)!=1);
                if(insert) {
                    flag = false;
                }
            }
            return flag;
        }
        else {
            return false;
        }
    }

    /**
     * 删除书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public boolean delete(BookDetail bookDetail) {
        boolean permission = userSecurityService.checkIdCategoryStaff(bookDetail.getLoginedUserId());
        if(permission) {
            boolean flag = (bookDetailDao.delete(bookDetail)==1);
            return flag;
        }
        else {
            return false;
        }
    }

    /**
     * 修改书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public boolean update(BookDetail bookDetail) {
        boolean permission = userSecurityService.checkIdCategoryStaff(bookDetail.getLoginedUserId());
        if(permission) {
            boolean flag = (bookDetailDao.update(bookDetail)==1);
            return flag;
        }
        else {
            return false;
        }
    }

    /**
     * 书籍借出
     *
     * @param bookDetail
     * @return
     */
    @Override
    public boolean rentBook(BookDetail bookDetail) {
        boolean permission = userSecurityService.checkIdCategoryStaff(bookDetail.getLoginedUserId())
                                || bookDetail.getLoginedUserId().equals(bookDetail.getRentUserId());
        if(permission) {
            BookDetail bookDetailTmp = bookDetailDao.selectById(bookDetail);
            if (bookDetailTmp.getRent().equals(RentConst.UNRENT)) {
                boolean rentable = (bookDetail.getRentUserId() != null);
                if (rentable) {
                    BookDetail bookDetailUpdate = new BookDetail();
                    bookDetailUpdate.setId(bookDetail.getId());
                    bookDetailUpdate.setRentUserId(bookDetail.getRentUserId());
                    bookDetailUpdate.setRentTime(new Date());

                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime(new Date());
                    rightNow.add(Calendar.MONTH, 1);

                    bookDetailUpdate.setRentBackTime(rightNow.getTime());
                    bookDetailUpdate.setRent(RentConst.RENTED);
                    int row = bookDetailDao.update(bookDetailUpdate);
                    return (row == 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 书籍归还
     *
     * @param bookDetail
     * @return
     */
    @Override
    public boolean backBook(BookDetail bookDetail) {
        boolean permission = userSecurityService.checkIdCategoryStaff(bookDetail.getLoginedUserId())
                || bookDetail.getLoginedUserId().equals(bookDetail.getRentUserId());
        if(permission) {
            BookDetail bookDetailTmp = bookDetailDao.selectById(bookDetail);
            if (bookDetailTmp.getRent().equals(RentConst.RENTED)) {
                BookDetail bookDetailUpdate = new BookDetail();
                bookDetailUpdate.setId(bookDetail.getId());
                bookDetailUpdate.setRent(RentConst.UNRENT);

                int row = bookDetailDao.back(bookDetailUpdate);
                return (row == 1);
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 通过ID查找书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public BookDetail selectById(BookDetail bookDetail) {
        return bookDetailDao.selectById(bookDetail);
    }

    /**
     * 获取已借出书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public List<BookDetail> selectRent(BookDetail bookDetail) {
        List<BookDetail> list = bookDetailDao.selectRent(bookDetail);
        if(list.isEmpty()) {
            return null;
        }
        else {
            return list;
        }
    }

    /**
     * 获取未借出书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public List<BookDetail> selectUnRent(BookDetail bookDetail) {
        List<BookDetail> list = bookDetailDao.selectUnRent(bookDetail);
        if(list.isEmpty()) {
            return null;
        }
        else {
            return list;
        }
    }

    /**
     * 模糊查询
     * TODO：模糊查询
     *
     * @param bookSearchDto
     * @return
     */
    @Override
    public List<BookDetail> selectLike(BookSearchDto bookSearchDto) {
        List<BookDetail> list = bookDetailDao.selectLike(bookSearchDto);
        if(list.isEmpty()) {
            return null;
        }
        else {
            return list;
        }
    }

    /**
     * 获取所有书籍
     *
     * @param bookDetail
     * @return
     */
    @Override
    public List<BookDetail> selectAll(BookDetail bookDetail) {
        List<BookDetail> list = bookDetailDao.selectAll(bookDetail);
        if(list.isEmpty()) {
            return null;
        }
        else {
            return list;
        }
    }
}
