package cn.albumenj.dao;

import cn.albumenj.bean.UserDetail;
import cn.albumenj.bean.UserSecurity;

import java.util.List;

/**
 * @author Albumen
 */
public interface UserDetailDao {
    /**
     * 查询目标用户
     *
     * @param userDetail
     * @return
     */
    UserDetail select(UserDetail userDetail);

    /**
     * 通过安全组权限查找用户
     *
     * @param userDetail
     * @return
     */
    List<UserDetail> selectByPermission(UserDetail userDetail);

    /**
     * 添加用户详情
     *
     * @param userDetail
     * @return
     */
    int insert(UserDetail userDetail);

    /**
     * 删除用户详情
     *
     * @param userDetail
     * @return
     */
    int delete(UserDetail userDetail);

    /**
     * 更新用户详情
     *
     * @param userDetail
     * @return
     */
    int update(UserDetail userDetail);
}
