package cn.albumen.library.service.impl;

import cn.albumen.library.bean.UserDetail;
import cn.albumen.library.constant.SexConst;
import cn.albumen.library.dao.UserDetailDao;
import cn.albumen.library.service.UserDetailService;
import cn.albumen.library.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Albumen
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    UserDetailDao userDetailDao;

    @Autowired
    UserSecurityService userSecurityService;

    /**
     * 添加空详情（同步添加安全组用户）
     *
     * @param userId
     * @return
     */
    @Override
    public boolean addBlank(Integer userId) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);
        userDetail.setLocation("");
        userDetail.setSex(SexConst.UNKNOWN);
        userDetail.setName("");
        userDetailDao.insert(userDetail);
        return false;
    }

    /**
     * 修改详情内容
     *
     * @param userDetail
     * @return
     */
    @Override
    public boolean update(UserDetail userDetail) {
        UserDetail userDetailTmp = userDetailDao.select(userDetail);
        if (userDetailTmp == null || (!userDetail.getId().equals(userDetailTmp.getId()))) {
            return false;
        }
        boolean flag = userSecurityService.checkIDPermission(userDetail.getLoginUserId(), userDetail.getUserId());
        if (flag) {
            int row = userDetailDao.update(userDetail);
            return (row == 1);
        } else {
            return false;
        }
    }

    /**
     * 删除详情
     *
     * @param userDetail
     * @return
     */
    @Override
    public boolean delete(UserDetail userDetail) {
        boolean flag = userSecurityService.checkIDPermission(userDetail.getLoginUserId(), userDetail.getUserId());
        if (flag) {
            userDetail = userDetailDao.selectById(userDetail);
            int row = userDetailDao.delete(userDetail);
            return (row == 1);
        } else {
            return false;
        }
    }

    /**
     * 获取详情列表
     *
     * @param userDetail
     * @return
     */
    @Override
    public List<UserDetail> selectByPermission(UserDetail userDetail) {
        List<UserDetail> userDetails = userDetailDao.selectByPermission(userDetail);
        if (userDetails.isEmpty()) {
            return null;
        } else {
            return userDetails;
        }
    }

    /**
     * 通过安全组用户编号获取详情
     *
     * @param userDetail
     * @return
     */
    @Override
    public UserDetail selectById(UserDetail userDetail) {
        boolean flag = userSecurityService.checkIDPermission(userDetail.getLoginUserId(), userDetail.getUserId());
        if (flag) {
            return userDetailDao.select(userDetail);
        } else {
            return null;
        }
    }
}
