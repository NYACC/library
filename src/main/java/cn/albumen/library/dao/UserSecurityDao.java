package cn.albumen.library.dao;

import cn.albumen.library.bean.UserSecurity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Mapper
@Repository
public interface UserSecurityDao {
    /**
     * 通过编号查找用户
     *
     * @param userSecurity
     * @return 所查询到的用户
     */
    @Select({"select id, user_no, phone, mail, password, permission from user_security " +
            "   where user_no = #{userNo}"})
    UserSecurity selectByNo(UserSecurity userSecurity);

    /**
     * 通过ID查找用户
     *
     * @param userSecurity
     * @return 所查询到的用户
     */
    @Select({"select id, user_no, phone, mail, password, permission from user_security " +
            "   where id = #{id}"})
    UserSecurity selectById(UserSecurity userSecurity);

    /**
     * 通过ID查找用户
     *
     * @param id
     * @return 所查询到的用户
     */
    @Select({"select user_no, phone, mail, password, permission from user_security " +
            "   where id = #{id}"})
    UserSecurity selectByCuratorId(Integer id);

    /**
     * 添加安全组用户
     *
     * @param userSecurity
     * @return
     */
    @Insert({"insert into user_security(id, user_no, phone, mail, password, permission) " +
            "   values (#{id},#{userNo},#{phone},#{mail},#{password},#{permission})"})
    int addUser(UserSecurity userSecurity);

    /**
     * 修改安全组用户
     *
     * @param userSecurity
     * @return
     */
    @Update({"<script> " +
            "   update user_security" +
            "       <set>" +
            "           <if test=\"userNo != null\">" +
            "               user_no = #{userNo}," +
            "           </if>" +
            "           <if test=\"phone != null\">" +
            "               phone = #{phone}," +
            "           </if>" +
            "           <if test=\"mail != null\">" +
            "               mail = #{mail}," +
            "           </if>" +
            "           <if test=\"password != null\">" +
            "               password = #{password}," +
            "           </if>" +
            "           <if test=\"permission != null\">" +
            "               permission = #{permission}," +
            "           </if>" +
            "       </set>" +
            "   where id = #{id}" +
            "</script>"})
    int update(UserSecurity userSecurity);

    /**
     * 有限制性枚举
     *
     * @param userSecurity 当前登陆用户权限
     * @return
     */
    @Select({"<script>" +
            "   select * from user_security" +
            "   where permission > #{permission} or id = #{id}" +
            "   <if test=\"start != null and count != null\">" +
            "       limit #{start},#{count}" +
            "   </if>" +
            " </script>"})
    List<UserSecurity> selectLimited(UserSecurity userSecurity);

    /**
     * 计算用户量
     *
     * @param userSecurity 当前登陆用户，用于鉴定权限
     * @return
     */
    @Select({"select count(*) from user_security" +
            "   where permission > #{permission} or id = #{id}"})
    int countUser(UserSecurity userSecurity);

    /**
     * 删除用户
     *
     * @param userSecurity
     * @return
     */
    @Delete({"delete from user_security" +
            "   where id = #{id}"})
    int delete(UserSecurity userSecurity);
}
