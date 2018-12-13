package cn.albumen.library.dao;

import cn.albumen.library.bean.UserDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Repository
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
