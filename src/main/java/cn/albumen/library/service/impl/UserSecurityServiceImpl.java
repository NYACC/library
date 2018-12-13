package cn.albumen.library.service.impl;

import cn.albumen.library.bean.UserDetail;
import cn.albumen.library.bean.UserSecurity;
import cn.albumen.library.constant.PermissionConst;
import cn.albumen.library.dao.UserSecurityDao;
import cn.albumen.library.service.UserDetailService;
import cn.albumen.library.service.UserSecurityService;
import cn.albumen.library.util.Jwt;
import cn.albumen.library.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Albumen
 */
@Service
public class UserSecurityServiceImpl implements UserSecurityService {
    @Autowired
    UserSecurityDao userSecurityDao;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 验证登陆
     *
     * @param userSecurity
     * @return 返回查询结果
     */
    @Override
    public UserSecurity login(UserSecurity userSecurity) {

        if (userSecurity.getUserNo() == null) {
            return null;
        }
        UserSecurity result = userSecurityDao.selectByNo(userSecurity);
        boolean flag = (result.getPassword().compareTo(userSecurity.getPassword()) == 0);
        if (flag == false) {
            return null;
        } else {
            UserSecurity userSecurityDto = new UserSecurity();
            BeanUtils.copyProperties(result, userSecurityDto);
            String token = Jwt.create(result.getId().toString());
            if(token == null) {
                return null;
            }
            try {
                redisUtil.set(result.getId().toString(), token);
            }catch (Exception e){
                return null;
            }
            userSecurityDto.setToken(token);
            return userSecurityDto;
        }
    }

    /**
     * 登出
     *
     * @param userSecurity
     * @return 返回查询结果
     */
    @Override
    public boolean logout(UserSecurity userSecurity) {
        boolean ret = redisUtil.delete(userSecurity.getLoginedUserId().toString());
        return ret;
    }

    /**
     * 添加安全组用户
     *
     * @param userSecurity
     * @return
     */
    @Override
    public boolean addUser(UserSecurity userSecurity) {

        boolean flag = checkPermission(getPermission(userSecurity.getLoginedUserId()),
                userSecurity.getPermission());

        if (flag) {
            int row;
            try {
                row = userSecurityDao.addUser(userSecurity);
            }catch (DataAccessException e) {
                row = 0;
            }
            if(row == 1) {
                userSecurity = userSecurityDao.selectByNo(userSecurity);
                userDetailService.addBlank(userSecurity.getId());
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 修改安全组用户
     *
     * @param userSecurity
     * @return
     */
    @Override
    public boolean updateUser(UserSecurity userSecurity) {

        boolean flag = checkPermission(getPermission(userSecurity.getLoginedUserId()),
                userSecurity.getPermission());

        if(flag) {
            int row;
            try {
                row = userSecurityDao.update(userSecurity);
            }catch (DataAccessException e) {
                row = 0;
            }
            return (row == 1);
        }
        else {
            return false;
        }
    }

    /**
     * 防止越权
     *
     * @param login 已登录用户
     * @param operated 被操作用户
     * @return
     */
    private boolean checkPermission(Integer login,Integer operated) {
        boolean flag = login.equals(PermissionConst.ADMINISTRATOR)
                            ||(login.equals(PermissionConst.CURATOR)
                                    &&!operated.equals(PermissionConst.ADMINISTRATOR)
                            ||login.equals(operated));
        return flag;
    }

    /**
     * 防止越权
     *
     * @param login 已登录用户
     * @param operated 被操作用户
     * @return
     */
    @Override
    public boolean checkIDPermission(Integer login,Integer operated) {
        return checkPermission(getPermission(login),getPermission(operated));
    }

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    @Override
    public boolean checkIdAdministrator(Integer user) {
        boolean flag = (getPermission(user) == PermissionConst.ADMINISTRATOR);
        return flag;
    }

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    @Override
    public boolean checkIdCurator(Integer user) {
        boolean flag = (getPermission(user) == PermissionConst.CURATOR);
        return flag;
    }


    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    @Override
    public boolean checkIdCategoryStaff(Integer user) {
        boolean flag = (getPermission(user) == PermissionConst.CATEGORY_STAFF
                        || getPermission(user) == PermissionConst.CURATOR
                        || getPermission(user) == PermissionConst.ADMINISTRATOR);
        return flag;
    }

    /**
     * 防止越权
     *
     * @param user 已登录用户
     * @return
     */
    @Override
    public boolean checkIdCommon(Integer user) {
        boolean flag = (getPermission(user) == PermissionConst.COMMON);
        return flag;
    }

    /**
     * 获取ID对于权限
     *
     * @param id
     * @return
     */
    private int getPermission(Integer id){
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setId(id);
        UserSecurity result = userSecurityDao.selectById(userSecurity);
        if(result == null) {
            return 0;
        }
        else {
            return result.getPermission();
        }
    }

    /**
     * 有限制性枚举
     *
     * @param userSecurity 鉴定权限
     * @return
     */
    @Override
    public List<UserSecurity> selectList(UserSecurity userSecurity) {
        userSecurity.setId(userSecurity.getLoginedUserId());
        UserSecurity userSecurityTmp = userSecurityDao.selectById(userSecurity);
        if(userSecurity == null) {
            return null;
        }
        userSecurityTmp.setStart(userSecurity.getStart());
        userSecurityTmp.setCount(userSecurity.getCount());
        List<UserSecurity> result = userSecurityDao.selectLimited(userSecurityTmp);
        if(result.isEmpty()) {
            return null;
        }
        for(UserSecurity userSecurityTemp:result) {
            userSecurityTemp.setPassword(null);
        }
        return result;
    }

    /**
     * 计算用户量
     *
     * @param userSecurity 鉴定权限
     * @return
     */
    @Override
    public int selectCount(UserSecurity userSecurity) {
        userSecurity.setId(userSecurity.getLoginedUserId());
        UserSecurity userSecurityTmp = userSecurityDao.selectById(userSecurity);
        if(userSecurityTmp == null) {
            return 0;
        }
        return userSecurityDao.countUser(userSecurityTmp);
    }

    /**
     * 删除用户
     *
     * @param userSecurity
     * @return
     */
    @Override
    public boolean delete(UserSecurity userSecurity) {
        UserSecurity userSecurityLogin = new UserSecurity();
        userSecurityLogin.setId(userSecurity.getLoginedUserId());
        userSecurityLogin = userSecurityDao.selectById(userSecurityLogin);
        UserSecurity userSecurityOperated = new UserSecurity();
        userSecurityOperated.setId(userSecurity.getId());
        userSecurityOperated = userSecurityDao.selectById(userSecurityOperated);
        if(userSecurityLogin == null || userSecurityOperated == null) {
            return false;
        }
        else {
            if(checkPermission(userSecurityLogin.getPermission(),userSecurityOperated.getPermission())) {
                UserDetail userDetail = new UserDetail();
                userDetail.setUserId(userSecurity.getId());
                userDetail.setLoginedUserId(userSecurity.getLoginedUserId());
                userDetail = userDetailService.selectById(userDetail);
                userDetail.setLoginedUserId(userSecurity.getLoginedUserId());
                boolean flag = userDetailService.delete(userDetail);
                int row = userSecurityDao.delete(userSecurity);
                return (row == 1) && flag;
            }
            else {
                return false;
            }
        }
    }

}
