package cn.albumenj.controller;

import cn.albumenj.annotation.ControllerLog;
import cn.albumenj.bean.UserSecurity;
import cn.albumenj.constant.LevelConst;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.UserSecurityService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/usersecurity")
public class UserSecurityController {
    @Autowired
    UserSecurityService userSecurityService;

    @ControllerLog(description = "Login",level = LevelConst.CRITICAL)
    @RequestMapping (path = "/login", method = RequestMethod.POST)
    public PageCodeDto login(@RequestBody UserSecurity userSecurity){
        UserSecurity userSecurityDto = userSecurityService.login(userSecurity);
        PageCodeDto pageCodeDto;
        if(userSecurityDto != null) {
            userSecurityDto.setPassword(null);
            pageCodeDto = new PageCodeDto(PageCodeEnum.LOGIN_SUCCESS,userSecurityDto);
        }
        else {
            pageCodeDto = new PageCodeDto(PageCodeEnum.LOGIN_FAILED);
        }
        return pageCodeDto;
    }

    @ControllerLog(description = "Logout",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public PageCodeDto logout(UserSecurity userSecurity) {
        boolean ret= userSecurityService.logout(userSecurity);
        if(ret) {
            return new PageCodeDto(PageCodeEnum.LOGOUT_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.LOGOUT_FAILED);
        }

    }

    @ControllerLog(description = "AddUserSecurity",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.add(userSecurityService.addUser(userSecurity));
    }

    @ControllerLog(description = "ModifyUserSecurity",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/modify", method = RequestMethod.POST)
    public PageCodeDto modify(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.update(userSecurityService.updateUser(userSecurity));
    }

    @ControllerLog(description = "GetListUserSecurity",level = LevelConst.INFO)
    @RequestMapping(path = "/getlist", method = RequestMethod.POST)
    public PageCodeDto getList(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.get(userSecurityService.selectList(userSecurity));
    }

    @ControllerLog(description = "CountUserSecurity",level = LevelConst.INFO)
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public PageCodeDto count(UserSecurity userSecurity) {
        return PageCodeUtil.get(userSecurityService.selectCount(userSecurity));
    }

    @ControllerLog(description = "DeleteUserSecurity",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public PageCodeDto delete(UserSecurity userSecurity) {
        return PageCodeUtil.delete(userSecurityService.delete(userSecurity));
    }
}
