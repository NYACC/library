package cn.albumen.library.dao;

import cn.albumen.library.bean.BookDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Albumen
 */
@Mapper
@Repository
public interface BookDetailDao {
    /**
     * 添加书籍
     *
     * @param bookDetail
     * @return
     */
    @Insert({"insert into library.book_detail(isbn, name, category_id, author, rent)" +
            "   values (#{isbn},#{name},#{categoryId},#{author},#{rent})"})
    int add(BookDetail bookDetail);

    /**
     * 删除书籍
     *
     * @param bookDetail
     * @return
     */
    @Delete({"delete from book_detail where id = #{id}"})
    int delete(BookDetail bookDetail);

    /**
     * 修改书籍
     *
     * @param bookDetail
     * @return
     */
    @Update({"<script>" +
            "   update book_detail" +
            "        <set>" +
            "            <if test=\"isbn!=null\">" +
            "                isbn = #{isbn}," +
            "            </if>" +
            "            <if test=\"name!=null\">" +
            "                name = #{name}," +
            "            </if>" +
            "            <if test=\"categoryId!=null\">" +
            "                category_id = #{categoryId}," +
            "            </if>" +
            "            <if test=\"author!=null\">" +
            "                author = #{author}," +
            "            </if>" +
            "            <if test=\"rent!=null\">" +
            "                rent = #{rent}," +
            "            </if>" +
            "            <if test=\"rentTime!=null\">" +
            "                rent_time = #{rentTime}," +
            "            </if>\n" +
            "            <if test=\"rentBackTime!=null\">" +
            "                rent_back_time = #{rentBackTime}," +
            "            </if>\n" +
            "            <if test=\"rentUserId!=null\">" +
            "                rent_user_id = #{rentUserId}," +
            "            </if>" +
            "        </set>" +
            "   where id = #{id}" +
            "</script>"})
    int update(BookDetail bookDetail);

    /**
     * 归还书籍
     *
     * @param bookDetail
     * @return
     */
    @Update({"<script>" +
            "   update book_detail" +
            "        <set>" +
            "            <if test=\"rent!=null\">" +
            "                rent = #{rent}," +
            "            </if>" +
            "                rent_time = #{rentTime}," +
            "                rent_back_time = #{rentBackTime}," +
            "                rent_user_id = #{rentUserId}," +
            "        </set>" +
            "   where id = #{id}" +
            "</script>"})
    int back(BookDetail bookDetail);

    /**
     * 通过ID查找书籍
     *
     * @param bookDetail
     * @return
     */
    @Select({"SELECT" +
            "       book_detail.id," +
            "       book_detail.isbn," +
            "       book_detail.`name`," +
            "       book_detail.category_id," +
            "       book_detail.author," +
            "       book_detail.rent," +
            "       book_detail.rent_time," +
            "       book_detail.rent_back_time," +
            "       book_detail.rent_user_id," +
            "       book_category.`name` AS category" +
            "   FROM" +
            "       book_detail" +
            "           LEFT JOIN book_category" +
            "               ON book_category.id = book_detail.category_id" +
            "   WHERE book_detail.id = #{id}"})
    BookDetail selectById(BookDetail bookDetail);

    /**
     * 获取已借出书籍
     *
     * @param bookDetail 条数
     * @return
     */
    @Select({"<script>" +
            "SELECT" +
            "       book_detail.id," +
            "       book_detail.isbn," +
            "       book_detail.`name`," +
            "       book_detail.category_id," +
            "       book_detail.author," +
            "       book_detail.rent," +
            "       book_detail.rent_time," +
            "       book_detail.rent_back_time," +
            "       book_detail.rent_user_id," +
            "       book_category.`name` AS category" +
            "   FROM" +
            "       book_detail" +
            "           LEFT JOIN book_category" +
            "               ON book_category.id = book_detail.category_id" +
            "   WHERE" +
            "       book_detail.rent = '${@cn.albumen.library.constant.RentConst@RENTED}'" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<BookDetail> selectRent(BookDetail bookDetail);

    /**
     * 获取未借出书籍
     *
     * @param bookDetail 条数
     * @return
     */
    @Select({"<script>" +
            "SELECT" +
            "       book_detail.id," +
            "       book_detail.isbn," +
            "       book_detail.`name`," +
            "       book_detail.category_id," +
            "       book_detail.author," +
            "       book_detail.rent," +
            "       book_detail.rent_time," +
            "       book_detail.rent_back_time," +
            "       book_detail.rent_user_id," +
            "       book_category.`name` AS category" +
            "   FROM" +
            "       book_detail" +
            "           LEFT JOIN book_category" +
            "               ON book_category.id = book_detail.category_id" +
            "   WHERE" +
            "       book_detail.rent = '${@cn.albumen.library.constant.RentConst@UNRENT}'" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<BookDetail> selectUnRent(BookDetail bookDetail);

    /**
     * 查找书籍
     * TODO:模糊搜索
     *
     * @param bookDetail
     * @return
     */
    @Select({"<script>" +
            "SELECT" +
            "       book_detail.id," +
            "       book_detail.isbn," +
            "       book_detail.`name`," +
            "       book_detail.category_id," +
            "       book_detail.author," +
            "       book_detail.rent," +
            "       book_detail.rent_time," +
            "       book_detail.rent_back_time," +
            "       book_detail.rent_user_id," +
            "       book_category.`name` AS category" +
            "   FROM" +
            "       book_detail" +
            "           LEFT JOIN book_category" +
            "               ON book_category.id = book_detail.category_id" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<BookDetail> selectLike(BookDetail bookDetail);

    /**
     * 获取全部书籍
     *
     * @param bookDetail 条数
     * @return
     */
    @Select({"<script>" +
            "SELECT" +
            "       book_detail.id," +
            "       book_detail.isbn," +
            "       book_detail.`name`," +
            "       book_detail.category_id," +
            "       book_detail.author," +
            "       book_detail.rent," +
            "       book_detail.rent_time," +
            "       book_detail.rent_back_time," +
            "       book_detail.rent_user_id," +
            "       book_category.`name` AS category" +
            "   FROM" +
            "       book_detail" +
            "           LEFT JOIN book_category" +
            "               ON book_category.id = book_detail.category_id" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<BookDetail> selectAll(BookDetail bookDetail);
}
