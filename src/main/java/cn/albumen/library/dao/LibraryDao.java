package cn.albumen.library.dao;

import cn.albumen.library.bean.Library;
import cn.albumen.library.dto.LibraryDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Mapper
@Repository
public interface LibraryDao {
    /**
     * 通过图书馆ID查找
     *
     * @param library
     * @return
     */
    @Select({"select * from library where id = #{id}"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "curatorId", column = "curator_id"),
            @Result(property = "curator", column = "curator_id", one = @One(select = "cn.albumen.library.dao.UserSecurityDao.selectByCuratorId"))})
    LibraryDto selectById(Library library);

    /**
     * 通过馆长ID查找
     *
     * @param library
     * @return
     */
    @Select({"select * from library where curator_id = #{curatorId}"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "curatorId", column = "curator_id"),
            @Result(property = "curator", column = "curator_id", one = @One(select = "cn.albumen.library.dao.UserSecurityDao.selectByCuratorId"))})
    List<LibraryDto> selectByCurator(Library library);

    /**
     * 有限制地获取图书馆
     *
     * @param library 条数
     * @return
     */
    @Select({"<script>" +
            " select * from library" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            " </script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "curatorId", column = "curator_id"),
            @Result(property = "curator", column = "curator_id", one = @One(select = "cn.albumen.library.dao.UserSecurityDao.selectByCuratorId"))})
    List<LibraryDto> selectLimit(Library library);

    /**
     * 添加图书馆
     *
     * @param library
     * @return
     */
    @Delete("insert into library.library(name, location, curator_id)" +
            "   values (#{name},#{location},#{curatorId})")
    int add(Library library);

    /**
     * 删除图书馆
     *
     * @param library
     * @return
     */
    @Delete({"delete from library.library where id = #{id}"})
    int delete(Library library);

    /**
     * 修改图书馆
     *
     * @param library
     * @return
     */
    @Update({"<script>" +
            "   update library.library" +
            "        <set>" +
            "            <if test=\"name!=null\">" +
            "                name = #{name}," +
            "            </if>" +
            "            <if test=\"location!=null\">" +
            "                location = #{location}," +
            "            </if>" +
            "            <if test=\"curatorId!=null\">" +
            "                curator_id = #{curatorId}," +
            "            </if>" +
            "        </set>" +
            "   where id = #{id}" +
            "</script>"})
    int update(Library library);

    /**
     * 获取图书馆数量
     *
     * @return
     */
    @Select({"select count(*) from library.library"})
    int count();
}
