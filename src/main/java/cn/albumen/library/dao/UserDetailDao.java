package cn.albumen.library.dao;

import cn.albumen.library.bean.UserDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Mapper
@Repository
public interface UserDetailDao {
    /**
     * 查询目标用户
     *
     * @param userDetail
     * @return
     */
    @Select({"select id,user_id,sex,name,location from user_detail" +
            "   where user_id = #{userId}"})
    UserDetail select(UserDetail userDetail);

    /**
     * 通过编号查询用户详情
     *
     * @param userDetail
     * @return
     */
    @Select({"select id,user_id,sex,name,location from user_detail" +
            "   where id = #{id}"})
    UserDetail selectById(UserDetail userDetail);

    /**
     * 通过安全组权限查找用户
     *
     * @param userDetail
     * @return
     */
    @Select({"<script>" +
            "   select c.id,c.user_id,c.sex,c.name,c.location" +
            "   from user_detail c,user_security s" +
            "   where " +
            "       c.user_id = s.id " +
            "       and " +
            "       s.permission >= " +
            "       (select permission from user_security where id = #{loginUserId})" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            "</script>"})
    List<UserDetail> selectByPermission(UserDetail userDetail);

    /**
     * 添加用户详情
     *
     * @param userDetail
     * @return
     */
    @Insert({"insert into user_detail(user_id, sex, name, location)" +
            "   values (#{userId},#{sex},#{name},#{location})"})
    int insert(UserDetail userDetail);

    /**
     * 删除用户详情
     *
     * @param userDetail
     * @return
     */
    @Delete({"delete from user_detail where id = #{id}"})
    int delete(UserDetail userDetail);

    /**
     * 更新用户详情
     *
     * @param userDetail
     * @return
     */
    @Update({"<script>" +
            "   update user_detail" +
            "        <set>" +
            "            <if test=\"userId!=null\">" +
            "                user_id = #{userId}," +
            "            </if>" +
            "            <if test=\"sex!=null\">" +
            "                sex = #{sex}," +
            "            </if>" +
            "            <if test=\"name!=null\">" +
            "                name = #{name}," +
            "            </if>" +
            "            <if test=\"location!=null\">" +
            "                location = #{location}," +
            "            </if>" +
            "        </set>" +
            "   where id = #{id}" +
            "</script>"})
    int update(UserDetail userDetail);
}
