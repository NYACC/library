package cn.albumenj.dao;

import cn.albumenj.bean.BookDetail;

import java.util.List;


/**
 * @author Albumen
 */
public interface BookDetailDao {
    /**
     * 添加书籍
     *
     * @param bookDetail
     * @return
     */
    int add(BookDetail bookDetail);

    /**
     * 删除书籍
     *
     * @param bookDetail
     * @return
     */
    int delete(BookDetail bookDetail);

    /**
     * 修改书籍
     *
     * @param bookDetail
     * @return
     */
    int update(BookDetail bookDetail);

    /**
     * 归还书籍
     *
     * @param bookDetail
     * @return
     */
    int back(BookDetail bookDetail);

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
     * @param bookDetail 条数
     * @return
     */
    List<BookDetail> selectRent(BookDetail bookDetail);

    /**
     * 获取未借出书籍
     *
     * @param bookDetail 条数
     * @return
     */
    List<BookDetail> selectUnRent(BookDetail bookDetail);

    /**
     * 查找书籍
     * TODO:模糊搜索
     *
     * @param bookDetail
     * @return
     */
    List<BookDetail> selectLike(BookDetail bookDetail);

    /**
     * 获取全部书籍
     *
     * @param bookDetail 条数
     * @return
     */
    List<BookDetail> selectAll(BookDetail bookDetail);
}
