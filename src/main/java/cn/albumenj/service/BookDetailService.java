package cn.albumenj.service;

import cn.albumenj.bean.BookDetail;
import cn.albumenj.dto.BookSearchDto;

import java.util.List;

/**
 * @author Albumen
 */
public interface BookDetailService {
    /**
     * 添加书籍
     *
     * @param bookDetail
     * @return
     */
    boolean add(BookDetail bookDetail);

    /**
     * 删除书籍
     *
     * @param bookDetail
     * @return
     */
    boolean delete(BookDetail bookDetail);

    /**
     * 修改书籍
     *
     * @param bookDetail
     * @return
     */
    boolean update(BookDetail bookDetail);

    /**
     * 书籍借出
     *
     * @param bookDetail
     * @return
     */
    boolean updateRentBook(BookDetail bookDetail);

    /**
     * 书籍归还
     *
     * @param bookDetail
     * @return
     */
    boolean updateBackBook(BookDetail bookDetail);

    /**
     * 通过ID查找书籍
     *
     * @param bookDetail
     * @return
     */
    BookDetail selectById(BookDetail bookDetail);

    /**
     * 获取已借出书籍
     *
     * @param bookDetail
     * @return
     */
    List<BookDetail> selectRent(BookDetail bookDetail);

    /**
     * 获取未借出书籍
     *
     * @param bookDetail
     * @return
     */
    List<BookDetail> selectUnRent(BookDetail bookDetail);

    /**
     * 模糊查询
     * TODO：模糊查询
     * @param bookSearchDto
     * @return
     */
    List<BookDetail> selectLike(BookSearchDto bookSearchDto);

    /**
     * 获取所有书籍
     *
     * @param bookDetail
     * @return
     */
    List<BookDetail> selectAll(BookDetail bookDetail);
}
