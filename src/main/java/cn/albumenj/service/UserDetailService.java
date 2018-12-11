package cn.albumenj.service;

import cn.albumenj.bean.UserDetail;

import java.util.List;

/**
 * @author Albumen
 */
public interface UserDetailService {
    /**
     * 添加空详情（同步添加安全组用户）
     *
     * @param userId
     * @return
     */
    boolean addBlank(Integer userId);

    /**
     * 修改详情内容
     *
     * @param userDetail
     * @return
     */
    boolean update(UserDetail userDetail);

    /**
     * 删除详情
     *
     * @param userDetail
     * @return
     */
    boolean delete(UserDetail userDetail);

    /**
     * 获取详情列表
     *
     * @param userDetail
     * @return
     */
    List<UserDetail> selectByPermission(UserDetail userDetail);

    /**
     * 通过安全组用户ID获取详情
     *
     * @param userDetail
     * @return
     */
    UserDetail selectById(UserDetail userDetail);
}
