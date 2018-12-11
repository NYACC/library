package cn.albumenj.dao;

import cn.albumenj.bean.UserSecurity;

import java.util.List;

/**
 * @author Albumen
 */
public interface UserSecurityDao {
    /**
     * 通过编号查找用户
     *
     * @param userSecurity
     * @return 所查询到的用户
     */
    UserSecurity selectByNo(UserSecurity userSecurity);

    /**
     * 通过ID查找用户
     *
     * @param userSecurity
     * @return 所查询到的用户
     */
    UserSecurity selectById(UserSecurity userSecurity);

    /**
     * 添加安全组用户
     *
     * @param userSecurity
     * @return
     */
    int addUser(UserSecurity userSecurity);

    /**
     * 修改安全组用户
     *
     * @param userSecurity
     * @return
     */
    int update(UserSecurity userSecurity);

    /**
     * 有限制性枚举
     *
     * @param userSecurity  当前登陆用户权限
     * @return
     */
    List<UserSecurity> selectLimited(UserSecurity userSecurity);

    /**
     * 计算用户量
     *
     * @param userSecurity  当前登陆用户，用于鉴定权限
     * @return
     */
    int countUser(UserSecurity userSecurity);

    /**
     * 删除用户
     * @param userSecurity
     * @return
     */
    int delete(UserSecurity userSecurity);
}
