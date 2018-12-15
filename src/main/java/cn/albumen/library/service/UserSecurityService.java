package cn.albumen.library.service;

import cn.albumen.library.bean.UserSecurity;

import java.util.List;

/**
 * @author Albumen
 */
public interface UserSecurityService {
    /**
     * 验证登陆
     *
     * @param userSecurity
     * @return 返回查询结果
     */
    UserSecurity login(UserSecurity userSecurity);

    /**
     * 登出
     *
     * @param userSecurity
     * @return 返回查询结果
     */
    boolean logout(UserSecurity userSecurity);

    /**
     * 添加安全组用户
     *
     * @param userSecurity
     * @return
     */
    boolean addUser(UserSecurity userSecurity);

    /**
     * 修改安全组用户
     *
     * @param userSecurity
     * @return
     */
    boolean updateUser(UserSecurity userSecurity);

    /**
     * 有限制性枚举
     *
     * @param userSecurity 鉴定权限
     * @return
     */
    List<UserSecurity> selectList(UserSecurity userSecurity);

    /**
     * 通过编号查找用户
     *
     * @param userSecurity
     * @return
     */
    UserSecurity selectById(UserSecurity userSecurity);

    /**
     * 计算用户量
     *
     * @param userSecurity 鉴定权限
     * @return
     */
    int selectCount(UserSecurity userSecurity);

    /**
     * 删除用户
     *
     * @param userSecurity
     * @return
     */
    boolean delete(UserSecurity userSecurity);

    /**
     * 防止越权
     *
     * @param login    已登录用户
     * @param operated 被操作用户
     * @return
     */
    boolean checkIDPermission(Integer login, Integer operated);

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    boolean checkIdAdministrator(Integer user);

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    boolean checkIdCurator(Integer user);

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    boolean checkIdCategoryStaff(Integer user);

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    boolean checkIdCommon(Integer user);
}
